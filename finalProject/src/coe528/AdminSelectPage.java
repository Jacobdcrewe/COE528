/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;

/**
 *
 * @author Andy
 */
public class AdminSelectPage extends Application {

    @Override
    public void start(Stage primaryStage) {

        Button btBk = new Button();
        btBk.setText("Books");
        btBk.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                adminAddBook d = new adminAddBook();
                d.start(primaryStage);
            }
        });

        Button btCs = new Button();
        btCs.setText("Customers");
        btCs.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                adminAddUser d = new adminAddUser();
                d.start(primaryStage);
                
            }
        });

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
        root.getChildren().add(btBk);
        root.getChildren().add(btCs);
        root.getChildren().add(btLo);
        root.setSpacing(15);

        
        Scene scene = new Scene(root, 500, 350);
        primaryStage.setWidth(500);
        primaryStage.setHeight(350);
        primaryStage.setTitle("Bookstore App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



}
