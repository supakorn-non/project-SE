package th.ac.ku.cs.sci.lukchinx.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import th.ac.ku.cs.sci.lukchinx.Model.Customer;

import java.io.IOException;

public class Menu {
    @FXML
    private Pane pane;
    @FXML
    private ImageView order, history, logout;

    private Customer customer;
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setOrder();
                setLogout();
                setHistory();
            }
        });
    }

    public void setHistory(){
        history.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/historyOrder.fxml"));
                Stage stage = (Stage) pane.getScene().getWindow();
                try {
                    stage.setScene(new Scene(loader.load(), 550, 750));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    public void setOrder() {
        order.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
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

    public void setCustomer(Customer customer){
        this.customer = customer;
    }
}
