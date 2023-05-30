package ma.barkouch.laptopapp.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ma.barkouch.laptopapp.entities.User;
import ma.barkouch.laptopapp.services.UserService;


public class LoginController {
    RedirectController rc = new RedirectController();
    final private UserService userService = new UserService();
    @FXML
    private Label mess;

    @FXML
    private TextField passwd;

    @FXML
    private Button reset;

    @FXML
    private Button submit;

    @FXML
    private TextField userName;

    @FXML
    void onReset(ActionEvent event) {
        userName.setText("");
        passwd.setText("");
    }

    @FXML
    void onSubmit(ActionEvent event)  {
        User user = new User(userName.getText(),passwd.getText());

        boolean testeur = userService.exists(user);
        if(testeur){
            rc.goTo("crudLayout/laptopsLayout.fxml",event);

        }else{
            mess.setText("User name or password incorrect!!");
        }
    }

}
