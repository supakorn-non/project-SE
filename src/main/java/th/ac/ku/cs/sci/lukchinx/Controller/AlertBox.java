package th.ac.ku.cs.sci.lukchinx.Controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlertBox {
    public static void display(String title,String message){
        Stage window =new Stage();
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(150);

        Label label =new Label();
        label.setText(message);
        Button closeButton=new Button("Close");
        closeButton.setOnAction(e -> window.close());
        closeButton.setStyle("-fx-background-color: #FB9956");

        VBox layout =new VBox(10);
        layout.getChildren().addAll(label,closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
