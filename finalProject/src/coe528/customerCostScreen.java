/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528;

import java.text.DecimalFormat;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;

/**
 *
 * @author Jacob
 */
public class customerCostScreen extends Application {
    DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public void start(Stage primaryStage) {
        
        String status;
        if(LoginScreen.pointsToPasson >= 1000) {
            status = "Gold";
        } else {
            status = "Silver";
        }

        Label totalCost = new Label("Total Cost: $" + df.format(customerScreen.totalCost));
        Label using = new Label("Points: " + LoginScreen.pointsToPasson + ", Status: " + status);


        Button btLo = new Button();
        btLo.setText("Logout");
        btLo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                LoginScreen d = new LoginScreen();
                d.start(primaryStage);
            }
        });

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(totalCost, using, btLo);
        root.setSpacing(15);

        
        Scene scene = new Scene(root, 500, 350);
        primaryStage.setWidth(500);
        primaryStage.setHeight(350);
        primaryStage.setTitle("Bookstore App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
