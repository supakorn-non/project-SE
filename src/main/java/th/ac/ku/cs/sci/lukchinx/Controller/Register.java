package th.ac.ku.cs.sci.lukchinx.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.cs.sci.lukchinx.Model.Customer;
import th.ac.ku.cs.sci.lukchinx.Service.CustomerService;

import java.io.IOException;
import java.util.regex.Pattern;

public class Register {
    private CustomerService customer = new CustomerService(new RestTemplate());

    @FXML
    private Pane pane;
    @FXML private TextField firstField, lastField, emailField, phoneField;
    @FXML private PasswordField passwordField, confirmField;
    @FXML private Button signUpBtn, loginBtn;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setLoginBtn();
                setSignUpBtnBtn();
            }
        });
    }

    public void setSignUpBtnBtn(){
        signUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!firstField.getText().isEmpty() && !lastField.getText().isEmpty() && !emailField.getText().isEmpty() && !phoneField.getText().isEmpty() && !passwordField.getText().isEmpty() && !confirmField.getText().isEmpty()){
                    if(!Pattern.matches("[a-zA-Z]", firstField.getText()) && !Pattern.matches("[a-zA-Z]", lastField.getText())
                            && Pattern.matches("[0-9]", phoneField.getText()) && Pattern.matches("[a-zA-Z0-9]", passwordField.getText()) && Pattern.matches("[a-zA-Z0-9]", confirmField.getText())){
                        System.out.println("invalid");
                    }else {
                        customer.createCustomer(new Customer(firstField.getText() + lastField.getText(), emailField.getText(), phoneField.getText(), passwordField.getText()));
                    }
                    firstField.clear();
                    lastField.clear();
                    emailField.clear();
                    phoneField.clear();
                    passwordField.clear();
                    confirmField.clear();
                }else{
                    System.out.println("fail");
                }
            }
        });
        signUpBtn.setOnMouseEntered(event -> signUpBtn.setStyle("-fx-background-color: #FEA058"));
        signUpBtn.setOnMouseExited(event -> signUpBtn.setStyle("-fx-background-color: #F56736"));
    }

    public void setLoginBtn(){
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/login.fxml"));
                Stage stage = (Stage) pane.getScene().getWindow();
                try {
                    stage.setScene(new Scene(loader.load(),550,750));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        loginBtn.setOnMouseEntered(event -> loginBtn.setStyle("-fx-background-color: #FB9956"));
        loginBtn.setOnMouseExited(event -> loginBtn.setStyle("-fx-background-color: #Fbb777"));
    }
}
