package ma.barkouch.laptopapp.views.crud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ma.barkouch.laptopapp.views.RedirectController;
import ma.barkouch.laptopapp.entities.Laptop;
import ma.barkouch.laptopapp.services.LaptopService;


public class CreateLaptop {
    LaptopService laptopService = new LaptopService();
    RedirectController redirectController = new RedirectController();

    @FXML
    private TextField marque;

    @FXML
    private TextField os;

    @FXML
    private ComboBox<String> size;

    @FXML
    private Label mess;

    @FXML
    void onReset(ActionEvent event) {
        redirectController.goTo("crudLayout/laptopsLayout.fxml",event);
    }

    @FXML
    void onSubmit(ActionEvent event)  {
        String getMarque = marque.getText();
        String getOs = os.getText();
        String getSize = size.getValue();
        if( getMarque.isBlank() || getSize == null || getSize.isBlank() || getOs.isBlank()){
            mess.setText("informations manquées");
        }else{
            Laptop laptop = new Laptop(0,marque.getText(),os.getText(),Laptop.Size.valueOf(size.getValue()));
            laptopService.insert(laptop);
            redirectController.goTo("crudLayout/laptopsLayout.fxml",event);

        }


    }

}
