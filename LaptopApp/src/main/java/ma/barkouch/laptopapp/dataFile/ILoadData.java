package ma.barkouch.laptopapp.dataFile;

import java.util.List;

public interface ILoadData<objectType> {

    void importFromJSON(String path);
    void importFromText(String path) ;
    void importFromExcel(String path);

    void exportToJSON(String path);
    void exportToText(String path);
    void exportToExcel(String path);
}
