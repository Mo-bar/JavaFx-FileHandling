package ma.barkouch.laptopapp.views.crud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ma.barkouch.laptopapp.views.RedirectController;
import ma.barkouch.laptopapp.entities.Laptop;
import ma.barkouch.laptopapp.services.LaptopService;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateController implements Initializable {
    RedirectController redirectController = new RedirectController();
    LaptopService laptopService = new LaptopService();

     int serial;
    @FXML
    private TextField marque;

    @FXML
    private Label mess;

    @FXML
    private TextField os;

    @FXML
    private ComboBox<String> size;

    public void loadLaptop(Laptop laptop){
        marque.setText(laptop.getMarque());
        os.setText(laptop.getOs());
        size.setValue(String.valueOf(laptop.getSize()));
        serial = laptop.getSerial();

    }

    @FXML
    void onReset(ActionEvent event) {
        redirectController.goTo("crudLayout/laptopsLayout.fxml",event);
    }

    @FXML
    void onSubmit(ActionEvent event) {
        Laptop laptop = new Laptop(serial,marque.getText(),os.getText(),Laptop.Size.valueOf(size.getValue()));
        laptopService.update(laptop);
        redirectController.goTo("crudLayout/laptopsLayout.fxml",event);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
