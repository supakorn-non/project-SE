package th.ac.ku.cs.sci.lukchinx.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {
    @FXML private Pane pane;
    @FXML private Button logoutBtn;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setLogoutBtn();
            }
        });
    }

    public void setLogoutBtn(){
        logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/start.fxml"));
                Stage stage = (Stage) pane.getScene().getWindow();
                try {
                    stage.setScene(new Scene(loader.load(), 550, 750));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        logoutBtn.setOnMouseEntered(event -> logoutBtn.setStyle("-fx-background-color: #FEA058"));
        logoutBtn.setOnMouseExited(event -> logoutBtn.setStyle("-fx-background-color: #F56736"));
    }
}
