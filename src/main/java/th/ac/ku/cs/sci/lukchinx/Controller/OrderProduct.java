package th.ac.ku.cs.sci.lukchinx.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import th.ac.ku.cs.sci.lukchinx.List.OrderList;
import th.ac.ku.cs.sci.lukchinx.Model.Customer;
import th.ac.ku.cs.sci.lukchinx.Model.Order;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

public class OrderProduct {
    private String totalprice;
    private String status = "";
    @FXML private Pane pane;
    @FXML private ImageView logout,History;
    @FXML
    private ComboBox<String> Typebox;
    @FXML
    private ComboBox<String> Sizebox;
    @FXML
    TextField Weightfield;


    @FXML
    public void initialize() throws URISyntaxException {
        this.Typebox.getItems().setAll(new String[]{"Male", "Female"});
        setLogout();
        setHistory();

    }

    @FXML
    public void chooseSex(ActionEvent event) {
        if ((Typebox.getValue()).equals("Male")) {
            this.Sizebox.getItems().setAll(new String[]{"6-8", "9-10", "11-12", "13-14", "15-16", "17-18", "19-20"});
        } else {
            this.Sizebox.getItems().setAll(new String[]{"20-24", "25-29", "30-34", "35-39", "40-50"});
        }
    }

    @FXML
    public void endBtnAction(ActionEvent event) throws IOException {
        String alert;
        if (!this.Typebox.getSelectionModel().isEmpty() && !this.Sizebox.getSelectionModel().isEmpty() && !this.Weightfield.getText().equals("")) {
            if (this.Weightfield.getText().contains(".")) {
                alert = "We sell integers, kilograms only.!";
                AlertBox.display("Alert!", alert);
            } else if (Integer.parseInt(this.Weightfield.getText()) <= 0) {
                alert = "invalid information!";
                AlertBox.display("Alert!", alert);
            } else if (Integer.parseInt(this.Weightfield.getText()) > 100) {
                alert = "Limited to a hundred kilos!";
                AlertBox.display("Alert!", alert);
            } else {
                if (((String) this.Typebox.getValue()).equals("Male")) {
                    if (((String) this.Sizebox.getValue()).equals("6-8")) {
                        this.totalprice = Integer.parseInt(this.Weightfield.getText()) * 450 + "";
                    } else if (((String) this.Sizebox.getValue()).equals("9-10")) {
                        this.totalprice = Integer.parseInt(this.Weightfield.getText()) * 400 + "";
                    } else if (((String) this.Sizebox.getValue()).equals("11-12")) {
                        this.totalprice = Integer.parseInt(this.Weightfield.getText()) * 380 + "";
                    } else if (((String) this.Sizebox.getValue()).equals("13-14")) {
                        this.totalprice = Integer.parseInt(this.Weightfield.getText()) * 360 + "";
                    } else if (((String) this.Sizebox.getValue()).equals("15-16")) {
                        this.totalprice = Integer.parseInt(this.Weightfield.getText()) * 340 + "";
                    } else if (((String) this.Sizebox.getValue()).equals("17-18")) {
                        this.totalprice = Integer.parseInt(this.Weightfield.getText()) * 300 + "";
                    } else if (((String) this.Sizebox.getValue()).equals("19-20")) {
                        this.totalprice = Integer.parseInt(this.Weightfield.getText()) * 280 + "";
                    }
                } else if (((String) this.Typebox.getValue()).equals("Female")) {
                    if (((String) this.Sizebox.getValue()).equals("20-24")) {
                        this.totalprice = Integer.parseInt(this.Weightfield.getText()) * 250 + "";
                    } else if (((String) this.Sizebox.getValue()).equals("25-29")) {
                        this.totalprice = Integer.parseInt(this.Weightfield.getText()) * 230 + "";
                    } else if (((String) this.Sizebox.getValue()).equals("30-34")) {
                        this.totalprice = Integer.parseInt(this.Weightfield.getText()) * 200 + "";
                    } else if (((String) this.Sizebox.getValue()).equals("35-39")) {
                        this.totalprice = Integer.parseInt(this.Weightfield.getText()) * 180 + "";
                    } else if (((String) this.Sizebox.getValue()).equals("40-50")) {
                        this.totalprice = Integer.parseInt(this.Weightfield.getText()) * 150 + "";
                    }
                } else if (((String) this.Sizebox.getValue()).equals((Object) null)) {
                    alert = "Information uncompleted!";
                    AlertBox.display("Alert!", alert);
                }

                this.Getticket();
                Button b;
                Stage stage;
                FXMLLoader loader;
                if (this.status.equals("1")) {
                    Order order = new Order(Typebox.getValue(), Sizebox.getValue(), Integer.parseInt(Weightfield.getText()), Double.parseDouble(totalprice));
                    OrderList.setOrder(order);
                    b = (Button)event.getSource();
                    stage = (Stage)b.getScene().getWindow();
                    loader = new FXMLLoader(this.getClass().getResource("/templates/payment.fxml"));
                    Payment payment = loader.getController();
                    stage.setScene(new Scene((Parent)loader.load(), 550, 750));
                    stage.setResizable(false);
                } else {
                    b = (Button)event.getSource();
                    stage = (Stage)b.getScene().getWindow();
                    loader = new FXMLLoader(this.getClass().getResource("/templates/orderProduct.fxml"));
                    stage.setScene(new Scene((Parent)loader.load(), 550, 750));
                    stage.show();
                }
            }
        }
    }

    public void Getticket() {
        final Stage window = new Stage();
        window.setTitle("Order confirmation");
        window.setMinWidth(300.0D);
        window.setMinHeight(200.0D);
        Label message = new Label();
        message.setText("All prices "+totalprice+" baht, confirm the order?");
        Button SubmitButton = new Button("SUBMIT");
        SubmitButton.setStyle("-fx-background-color:    #ff4a5f");
        SubmitButton.setTextFill(Paint.valueOf("ffffff"));
        Button CancleButton = new Button("CANCEL");
        CancleButton.setStyle("-fx-background-color:   #ffffff");
        SubmitButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                OrderProduct.this.status = "1";
                window.close();
            }
        });
        CancleButton.setOnAction((e) -> {
            window.close();
        });
        VBox layout = new VBox(30.0D);
        layout.getChildren().addAll(new Node[]{message});
        HBox hBox = new HBox();
        hBox.setSpacing(35.0D);
        hBox.getChildren().addAll(new Node[]{CancleButton, SubmitButton});
        hBox.setAlignment(Pos.CENTER);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().add(hBox);
        layout.setStyle("-fx-background-color: pink");
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
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
    public void setHistory(){
        History.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/menu.fxml"));
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
