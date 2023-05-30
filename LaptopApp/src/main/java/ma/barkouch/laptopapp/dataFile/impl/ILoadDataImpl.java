package ma.barkouch.laptopapp.dataFile.impl;

import com.google.gson.Gson;
import ma.barkouch.laptopapp.dataFile.ILoadData;
import ma.barkouch.laptopapp.entities.Laptop;
import ma.barkouch.laptopapp.model.Dao;
import ma.barkouch.laptopapp.model.impl.LaptopDaoImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ILoadDataImpl implements ILoadData<Laptop> {
    Dao<Laptop,Integer> laptopService = new LaptopDaoImpl();
    static XSSFRow row;

    public void createFile(String path)  {
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        }catch (IOException exp){
            System.out.println(exp);
        }
    }

    @Override
    public void importFromJSON(String path) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) obj;

            String jsonObjectList = (String) jsonObject.get("Laptops").toString();

            Gson gson = new Gson();
            JSONObject[] empArray = gson.fromJson(jsonObjectList, JSONObject[].class);

            for (int i = 0; i< empArray.length ; i++){
                 Laptop laptop = new Laptop(
                         0,
                         empArray[i].get("marque").toString(),
                         empArray[i].get("os").toString(),
                         Laptop.Size.valueOf(empArray[i].get("size").toString())
                         );
                laptopService.insert(laptop);
            }
        }catch (IOException exp){
            System.out.println(exp);
        }catch (ParseException exp){
            System.out.println(exp);
        }
    }

    @Override
    public void importFromText(String path)  {
        try{
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));

            List<Laptop> list = new ArrayList<>();
            Laptop laptop = null;
            String readLine = br.readLine();

            while(readLine != null){

                String [] laps  = readLine.split("\\|");

                laptop = new Laptop();
                laptop.setMarque(laps[0].trim());
                laptop.setOs(laps[1].trim());
                laptop.setSize(Laptop.Size.valueOf(laps[2].trim()));

                list.add(laptop);
                readLine = br.readLine();
            }
            list.stream().forEach(lap -> laptopService.insert(lap));

        }catch (IOException exp){
            System.out.println(exp);
        }
    }

    @Override
    public void importFromExcel(String path) {
        try(FileInputStream fis = new FileInputStream(path))
        {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet spreadsheet = workbook.getSheetAt(0);
            for (int i = 1; i<= spreadsheet.getLastRowNum(); i++){
                row = spreadsheet.getRow(i);
                laptopService.insert(new Laptop(
                        0,
                        row.getCell(0).getStringCellValue(),
                        row.getCell(1).getStringCellValue(),
                        Laptop.Size.valueOf(row.getCell(2).getStringCellValue().toString().trim())
                ));
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            System.out.println(e);
        }

    }

    @Override
    public void exportToJSON(String path) {
        try{
            List list = laptopService.findAll();
            createFile(path);
            Gson gson = new Gson();
            /*String jsonStr = gson.toJson(list);*/
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            list.stream().map(lap ->
                gson.toJson(lap)
            );
            bw.write(list.toString());
            bw.flush();
            bw.close();
        }catch (IOException exp){
            System.out.println(exp);
        }
    }
    @Override
    public void exportToText(String path) {
        List<Laptop> list = laptopService.findAll();
    try{
        createFile(path);
        FileWriter fileWriter = new FileWriter(path);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (Laptop laptop : list) {
            String string = laptop.toString();
            bufferedWriter.write(string);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        bufferedWriter.close();
    }catch (IOException exp){System.out.println(exp);}

    }

    @Override
    public void exportToExcel(String path) {
        createFile(path);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Laptops");

        List<Laptop> laptops;
        laptops = laptopService.findAll();

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("#");
        headerRow.createCell(1).setCellValue("Marque");
        headerRow.createCell(2).setCellValue("Operating system");
        headerRow.createCell(3).setCellValue("Taille");

        int rowNum = 1;
        for (Laptop laptop : laptops) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(laptop.getSerial());
            row.createCell(1).setCellValue(laptop.getMarque());
            row.createCell(2).setCellValue(laptop.getOs());
            row.createCell(3).setCellValue(String.valueOf(laptop.getSize()));

        }

        try (FileOutputStream outputStream = new FileOutputStream(path)) {
            workbook.write(outputStream);
            System.out.println("Laptops exporte bien fait");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
