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
import java.util.regex.Matcher;
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

    public boolean containSpecialChar(String input, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        return m.find();
    }

    public void setSignUpBtnBtn(){
        signUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!emailField.getText().isEmpty() && !passwordField.getText().isEmpty() && !confirmField.getText().isEmpty()
                        && !emailField.getText().isEmpty() && !firstField.getText().isEmpty() && !lastField.getText().isEmpty()) {
                    if (!containSpecialChar(firstField.getText(), "[A-Z][a-z]")) {
                        AlertBox.display("Alert!", "First name is in correct!");
                        firstField.clear();
                    }
                    else if (!containSpecialChar(lastField.getText(), "[A-Z][a-z]")) {
                        AlertBox.display("Alert!", "Last name is in correct!");
                        lastField.clear();
                    }
                    else if (emailField.getText().split("@").length != 2) {
                        AlertBox.display("Alert!", "Email is incorrect! ");
                        emailField.clear();
                    }
                    else if (!containSpecialChar(phoneField.getText(), "[0-9]*") || phoneField.getLength() != 10) {
                        AlertBox.display("Alert!", "     Phone must be number! & has a length of 10 characters");
                        phoneField.clear();
                    }
                    else if (passwordField.getText().length() < 4) {
                        AlertBox.display("Alert!", "Please enter password at least 4 character!");
                        passwordField.clear();
                    }
                    else if(!containSpecialChar(passwordField.getText(), "[a-z0-9]")) {
                        AlertBox.display("Alert!", "     Passwords should contain lowercase Uppercase letters and number \n                                       for security reasons !");
                        passwordField.clear();
                    }
                    else if (!passwordField.getText().equals(confirmField.getText())) {
                        AlertBox.display("Alert!", "Password is not match !");
                        confirmField.clear();
                    }
                    else {
                        Customer record = new Customer(emailField.getText(),firstField.getText()+" "+lastField.getText(),phoneField.getText(),passwordField.getText());
                        customer.createCustomer(record);
                        AlertBox.display("Alert!", "Success !");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/login.fxml"));
                        Stage stage = (Stage) pane.getScene().getWindow();
                        try {
                            stage.setScene(new Scene(loader.load(), 550, 750));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
                else {
                    AlertBox.display("Alert!", "Incomplete information !");
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
                    stage.setScene(new Scene(loader.load(), 550, 750));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        loginBtn.setOnMouseEntered(event -> loginBtn.setStyle("-fx-background-color: #FB9956"));
        loginBtn.setOnMouseExited(event -> loginBtn.setStyle("-fx-background-color: #Fbb777"));
    }
}
