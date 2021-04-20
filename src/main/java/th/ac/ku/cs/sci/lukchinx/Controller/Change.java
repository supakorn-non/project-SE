package th.ac.ku.cs.sci.lukchinx.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.cs.sci.lukchinx.Model.Customer;
import th.ac.ku.cs.sci.lukchinx.Service.CustomerService;

import java.io.IOException;

public class Change {
    private CustomerService customerService = new CustomerService(new RestTemplate());
    @FXML private TextField email, otp, newPass, confirm;
    @FXML private Button sendBtn, confirmBtn, loginBtn;
    @FXML private Pane pane;
    @FXML private Text warning;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setSendBtn();
                setConfirmBtn();
                setLoginBtn();
            }
        });
    }

    public void setSendBtn(){
        sendBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Customer customer = new Customer();
                customer.setEmail(email.getText());
                customerService.sendOTP(customer);
                warning.setOpacity(1);
            }
        });
        sendBtn.setOnMouseEntered(event -> sendBtn.setStyle("-fx-background-color: #FEA058"));
        sendBtn.setOnMouseExited(event -> sendBtn.setStyle("-fx-background-color: #F56736"));
    }

    public void setConfirmBtn(){
        confirmBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!otp.getText().isEmpty() && !newPass.getText().isEmpty() && !confirm.getText().isEmpty()) {
                    Customer customer = new Customer();
                    customer.setEmail(email.getText());
                    customer.setOTP(otp.getText());
                    if (newPass.getText().equals(confirm.getText())) {
                        customer.setPassword(newPass.getText());
                        customerService.changePassword(customer);
                        AlertBox.display("Alert!","Password change successfully.");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/login.fxml"));
                        Stage stage = (Stage) pane.getScene().getWindow();
                        try {
                            stage.setScene(new Scene(loader.load(), 550, 750));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        AlertBox.display("Alert!","Password is not match.");
                    }
                }
            }
        });
        confirmBtn.setOnMouseEntered(event -> confirmBtn.setStyle("-fx-background-color: #FEA058"));
        confirmBtn.setOnMouseExited(event -> confirmBtn.setStyle("-fx-background-color: #F56736"));
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
