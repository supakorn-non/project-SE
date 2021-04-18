package th.ac.ku.cs.sci.lukchinx.Controller;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.lukchinx.Application.StageCaller;

import java.io.IOException;

@Component
@FxmlView
@Controller
public class Start {
    private ApplicationContext ac;
    private double opacity = 1;
    @FXML
    Pane pane ;
    @FXML private Circle circle;
    @FXML private ImageView product;
    @FXML private Text text1, text2;


//    @Autowired
//    public void Start(ApplicationContext applicationContext, @Value("classpath:templates/login.fxml")Resource login){
//        this.ac = applicationContext;
//        StageCaller caller = new StageCaller(login, ac);
//    }


    public void initialize(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                AnimationTimer timer = new AnimationTimer() {
                    @SneakyThrows
                    @Override
                    public void handle(long now) {
                        doHandle();
                    }
                    private void doHandle() throws IOException {
                        opacity -= 0.004;
                        circle.opacityProperty().set(opacity);
                        product.opacityProperty().set(opacity);
                        text1.opacityProperty().set(opacity);
                        text2.opacityProperty().set(opacity);
                        if (opacity <= 0){
                            stop();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/login.fxml"));

                            Stage stage = (Stage) pane.getScene().getWindow();
                            try {
                                stage.setScene(new Scene(loader.load(), 550, 750));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                timer.start();
            }
        });
    }

}
