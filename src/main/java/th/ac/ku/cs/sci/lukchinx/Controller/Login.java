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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.cs.sci.lukchinx.List.CustomerList;
import th.ac.ku.cs.sci.lukchinx.Model.Customer;
import th.ac.ku.cs.sci.lukchinx.Model.Order;
import th.ac.ku.cs.sci.lukchinx.Service.CustomerService;

import java.io.IOException;

@FxmlView
@Component
@Controller
public class Login {
    private CustomerService customerService = new CustomerService(new RestTemplate());
    @FXML
    private Pane pane;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginBtn, signInBtn;
    @FXML
    private Text forgot;

    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setLoginBtn();
                setSignInBtn();
                setForgot();
            }
        });
    }

    public void setLoginBtn() {
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()){
                    Customer customer = customerService.login(new Customer(usernameField.getText(),passwordField.getText()));
                    if(customer != null) {
                        CustomerList.setCustomer(customer);
                        Order order = new Order();
                        order.setCid(CustomerList.getCustomer().getId());
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/menu.fxml"));
                        Stage stage = (Stage) pane.getScene().getWindow();
                        try {
                            stage.setScene(new Scene(loader.load(), 550, 750));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        AlertBox.display("Alert!","Email or Password is not correct!");
                    }
                }
            }
        });
        loginBtn.setOnMouseEntered(event -> loginBtn.setStyle("-fx-background-color: #FEA058"));
        loginBtn.setOnMouseExited(event -> loginBtn.setStyle("-fx-background-color: #F56736"));
    }

    public void setSignInBtn() {
        signInBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/register.fxml"));
                Stage stage = (Stage) pane.getScene().getWindow();
                try {
                    stage.setScene(new Scene(loader.load(), 550, 750));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        signInBtn.setOnMouseEntered(event -> signInBtn.setStyle("-fx-background-color: #FB9956"));
        signInBtn.setOnMouseExited(event -> signInBtn.setStyle("-fx-background-color: #Fbb777"));
    }

    public void setForgot() {
        forgot.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/change.fxml"));
                Stage stage = (Stage) pane.getScene().getWindow();
                try {
                    stage.setScene(new Scene(loader.load(), 550, 750));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        forgot.setOnMouseEntered(event -> forgot.setFill(Color.rgb(245, 103, 54)));
        forgot.setOnMouseExited(event -> forgot.setFill(Color.WHITE));
    }
}
