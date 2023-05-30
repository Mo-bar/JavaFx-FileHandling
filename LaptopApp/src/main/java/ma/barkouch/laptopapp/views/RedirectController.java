package ma.barkouch.laptopapp.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ma.barkouch.laptopapp.App;

import java.io.IOException;

public class RedirectController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void goTo(String filename, ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(App.class.getResource(filename));
            root = loader.load();
            //AddLapController newpage = loader.getController();
            //newpage.methode();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException exp){
            System.out.println(exp);
        }
    }
}
