package th.ac.ku.cs.sci.lukchinx.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.cs.sci.lukchinx.List.CustomerList;
import th.ac.ku.cs.sci.lukchinx.List.OrderList;
import th.ac.ku.cs.sci.lukchinx.Model.Customer;
import th.ac.ku.cs.sci.lukchinx.Model.Order;
import th.ac.ku.cs.sci.lukchinx.Service.OrderService;

import java.io.IOException;

public class Payment {
    private OrderService orderService = new OrderService(new RestTemplate());

    String datetext = "";
    @FXML
    private Pane pane;
    @FXML
    Text a, b;
    @FXML
    Text PriceText;
    @FXML
    Text NameText;
    @FXML
    TextField ReferenceCode;
    @FXML
    TextArea Address;
    @FXML
    TextField hour;
    @FXML
    TextField min;
    @FXML
    DatePicker DatePick;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                NameText.setText(CustomerList.getCustomer().getName());
                PriceText.setText(OrderList.getOrder().getPrice()+"");
            }
        });
    }

    @FXML
    public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/orderProduct.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(loader.load(), 550, 750));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void submitBtnAction(ActionEvent event) throws IOException {
        this.datetext = this.DatePick.getValue() + " ";
        String[] dataSplit = this.datetext.split("-");
        String alert;

        if (!this.ReferenceCode.getText().equals("") && !this.hour.getText().equals("") && !this.min.getText().equals("") && !this.datetext.equals("") && !this.Address.getText().equals("") ) {
                    if (this.ReferenceCode.getText().length() > 10) {
                        alert = "ReferenceCode out of range !";
                        AlertBox.display("Alert!", alert);
                    } else {
                        AlertBox.display("alert","Order success !");
                        Order order = new Order(CustomerList.getCustomer().getName(), Address.getText(), CustomerList.getCustomer().getTel(), OrderList.getOrder().getTypeShrimp(), OrderList.getOrder().getSizeShrimp(),OrderList.getOrder().getWeightShrimp(), OrderList.getOrder().getPrice());
                        orderService.createOrder(order);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/menu.fxml"));
                        Stage stage = (Stage) pane.getScene().getWindow();
                        try {
                            stage.setScene(new Scene(loader.load(), 550, 750));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

        } else {
            alert = "Information uncompleted !";
            AlertBox.display("Alert!", alert);
        }
    }

}
