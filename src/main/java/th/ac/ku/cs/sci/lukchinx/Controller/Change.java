package th.ac.ku.cs.sci.lukchinx.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.cs.sci.lukchinx.Service.CustomerService;

public class Change {
    private CustomerService customerService;
    @FXML private TextField search;
    @FXML private Button searchBtn;
    @FXML private Text name;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setSearchBtn();
            }
        });
    }

    public void setSearchBtn(){
        this.customerService = new CustomerService(new RestTemplate());
        searchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        searchBtn.setOnMouseEntered(event -> searchBtn.setStyle("-fx-background-color: #FEA058"));
        searchBtn.setOnMouseExited(event -> searchBtn.setStyle("-fx-background-color: #F56736"));
    }
}
