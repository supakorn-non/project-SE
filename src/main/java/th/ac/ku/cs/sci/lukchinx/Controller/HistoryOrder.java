package th.ac.ku.cs.sci.lukchinx.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.cs.sci.lukchinx.List.CustomerList;
import th.ac.ku.cs.sci.lukchinx.List.OrderList;
import th.ac.ku.cs.sci.lukchinx.Model.Customer;
import th.ac.ku.cs.sci.lukchinx.Model.Order;
import th.ac.ku.cs.sci.lukchinx.Service.OrderService;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@FxmlView
public class HistoryOrder {
    OrderService orderService = new OrderService(new RestTemplate());

    @FXML
    private Button backBtn;
    @FXML
    private Pane pane;
    @FXML
    private TableView<Order> tableview;
    @FXML private ImageView order, logout;

    @FXML
    private void initialize() {
        loadHistory();
        setOrder();
        setLogout();
    }


    private void loadHistory() {

        TableColumn<Order, String> ADDRESS = new TableColumn<>("Address");
        TableColumn<Order, String> TYPE = new TableColumn<>("Type");
        TableColumn<Order, Integer> WEIGHT = new TableColumn<>("Weight");
        TableColumn<Order, String> SIZE = new TableColumn<>("Size");
        TableColumn<Order, Double> PRICE = new TableColumn<>("Price");

        ADDRESS.setCellValueFactory(new PropertyValueFactory<>("address"));
        TYPE.setCellValueFactory(new PropertyValueFactory<>("typeShrimp"));
        WEIGHT.setCellValueFactory(new PropertyValueFactory<>("weightShrimp"));
        SIZE.setCellValueFactory(new PropertyValueFactory<>("sizeShrimp"));
        PRICE.setCellValueFactory(new PropertyValueFactory<>("price"));


        tableview.getColumns().addAll(ADDRESS, TYPE, WEIGHT, SIZE, PRICE);

        Collection<Order> data = new ArrayList<>();


        ResponseEntity<Order[]> response = new RestTemplate().getForEntity("http://localhost:8000/order/getAllById/" + CustomerList.getCustomer().getId(), Order[].class);

        for (Order i : response.getBody()) {
            data.add(i);
        }
//        data.addAll(response.getBody());
        tableview.getItems().addAll(data);

    }

    public void setOrder(){
        order.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/orderProduct.fxml"));
                Stage stage = (Stage) pane.getScene().getWindow();
                try {
                    stage.setScene(new Scene(loader.load(), 550, 750));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setLogout(){
        logout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/login.fxml"));
                Stage stage = (Stage) pane.getScene().getWindow();
                try {
                    stage.setScene(new Scene(loader.load(), 550, 750));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
