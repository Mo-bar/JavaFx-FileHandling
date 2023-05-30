package ma.barkouch.laptopapp.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ma.barkouch.laptopapp.dataFile.ILoadData;
import ma.barkouch.laptopapp.dataFile.impl.ILoadDataImpl;
import ma.barkouch.laptopapp.entities.Laptop;
import java.io.File;

public class ImpoExpo {

    final private String initialPath = "M:\\Java_\\LaptopApp\\src\\main\\resources\\dataSource";
    FileChooser fileChooser = new FileChooser();
    RedirectController redirectController = new RedirectController();
    ILoadData<Laptop> loadLaptop = new ILoadDataImpl();
    DirectoryChooser directoryChooser = new DirectoryChooser();
    String path;
    @FXML
    void exportExcel(ActionEvent event) {
        String path = getDirPath().concat("\\outputExcelSheet.xlsx");
        loadLaptop.exportToExcel(path);
    }

    @FXML
    void exportText(ActionEvent event) {
        String path = getDirPath().concat("\\outputText.txt");
        loadLaptop.exportToText(path);
    }

    @FXML
    void exportTextAsaJSON(ActionEvent event) {
        String path = getDirPath().concat("\\outputJsonAsText.txt");
        loadLaptop.exportToJSON(path);
    }

    @FXML
    void importExcel(ActionEvent event) {
        path = getFilePath("Excel fille","*.xlsx");
        loadLaptop.importFromExcel(path);

    }

    @FXML
    void importText(ActionEvent event) {
        path = getFilePath("Text fille","*.txt");
        loadLaptop.importFromText(path);
    }

    @FXML
    void importTextAsJSON(ActionEvent event) {
        path = getFilePath("Text fille","*.txt");
        loadLaptop.importFromJSON(path);
    }


    @FXML
    void close(ActionEvent event) {
        redirectController.goTo("crudLayout/laptopsLayout.fxml",event);
    }

    public String getFilePath(String desc,String ext ) {
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(desc, ext));
        fileChooser.setInitialDirectory(new File(initialPath));
        File file = fileChooser.showOpenDialog(new Stage());
        return file.getPath();
    }

    public String getDirPath(){
        directoryChooser.setInitialDirectory(new File(initialPath));
        String path = directoryChooser.showDialog(new Stage()).getPath();
        return path;
    }
}
