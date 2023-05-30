package ma.barkouch.laptopapp.views.crud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ma.barkouch.laptopapp.App;
import ma.barkouch.laptopapp.views.RedirectController;
import ma.barkouch.laptopapp.entities.Laptop;
import ma.barkouch.laptopapp.services.LaptopService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReadLaptops implements Initializable {
    LaptopService laptopService = new LaptopService();
    RedirectController redirectController = new RedirectController();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Laptop> laptopTable;

    @FXML
    private TableColumn<Laptop, String> marque;

    @FXML
    private TableColumn<Laptop, String>  os;

    @FXML
    private TableColumn<Laptop, Integer> serial;

    @FXML
    private TableColumn<Laptop, Laptop.Size> size;

    @FXML
    private Label mess;

    ObservableList<Laptop> laptops = FXCollections.observableArrayList(laptopService.findAll());
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        os.setCellValueFactory(new PropertyValueFactory<>("os"));
        marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        size.setCellValueFactory(new PropertyValueFactory<>("size"));
        serial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        laptopTable.setItems(laptops);
    }

    @FXML
    void AddLaptop(ActionEvent event)  {
        redirectController.goTo("crudLayout/addForm.fxml",event);

    }

    @FXML
    void deleteLaptop(ActionEvent event) {
        Laptop laptop = laptopTable.getSelectionModel().getSelectedItem();
        if(laptop == null){
            mess.setText("Sélectionner la ligne à supprimer");
        }else{
            mess.setText("  ");
            laptopService.deleteBySerial(laptop.getSerial());
            laptopTable.setItems(FXCollections.observableArrayList(laptopService.findAll()));
            laptopTable.refresh();

        }
    }


    @FXML
    void impoExpo(ActionEvent event) {
        redirectController.goTo("impoExpo/impoExpoLayout.fxml",event);
    }


    @FXML
    void updateLaptop(ActionEvent event) {
        Laptop laptop = laptopTable.getSelectionModel().getSelectedItem();
        if(laptop == null){
            mess.setText("Sélectionner la ligne à modifier");
        }else{
            mess.setText("  ");
            try{
                FXMLLoader loader = new FXMLLoader(App.class.getResource("crudLayout/editLayout.fxml"));
                root = loader.load();
                UpdateController controller = loader.getController();
                controller.loadLaptop(laptop);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch (IOException exp){
                System.out.println(exp);
            }
        }

    }
}
