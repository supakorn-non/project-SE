package th.ac.ku.cs.sci.lukchinx.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class StageListener implements ApplicationListener<StageReadyEvent> {


    private final Resource fxml ;
    private final ApplicationContext context;


    StageListener(@Value("classpath:templates/start.fxml") Resource fxml, ApplicationContext context) {
        this.fxml = fxml;
        this.context = context;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        try {
            Stage stage = stageReadyEvent.getStage();
            URL url = this.fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(context::getBean);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root,550,750);
            stage.setScene(scene);
            stage.setTitle("Shrimp Trading Company");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}