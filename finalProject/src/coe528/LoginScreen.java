/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
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
public class LoginScreen extends Application {

    static String usernameToPasson;
    static String passwordToPasson;
    static int pointsToPasson;
    //array list of users of type user
    ArrayList<User> users = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {

        userRecord r = userRecord.getInstance();

        //trying to read teh file and writing it to the arraylist
        try {
            FileReader reader = new FileReader(r.filename);
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String object = scan.nextLine();
                if (object.length() > 0) {
                    String[] str = object.split(" ");
                    users.add(new User(str[0], str[1], Integer.parseInt(str[2])));
                }
            }
        } catch (IOException e) {
            System.out.println("A read error occurred.");
        }

        Label wel = new Label("Welcome to the Bookstore!");

        Label us = new Label("Username:");
        TextField user = new TextField();
        user.setMaxWidth(200);
        user.setAlignment(Pos.CENTER_LEFT);
        HBox u = new HBox(us, user);
        u.setAlignment(Pos.CENTER);
        u.setSpacing(10);

        Label pa = new Label("Password:");
        PasswordField pass = new PasswordField();
        pass.setMaxWidth(200);
        pass.setAlignment(Pos.CENTER_LEFT);
        HBox p = new HBox(pa, pass);
        p.setAlignment(Pos.CENTER);
        p.setSpacing(10);

        Button btn = new Button();
        btn.setText("Log In");
        btn.setMaxSize(100, 10);
        btn.getTranslateX();
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                LoginScreen.usernameToPasson = user.getText();
                LoginScreen.passwordToPasson = pass.getText();
                for (User u : users) {
                    if (LoginScreen.usernameToPasson.equals("admin") && LoginScreen.passwordToPasson.equals("admin")) {
                        AdminSelectPage d = new AdminSelectPage();
                        d.start(primaryStage);
                    } else if (u.getUsername().equals(LoginScreen.usernameToPasson) && u.getPassword().equals(LoginScreen.passwordToPasson)) {
                        LoginScreen.pointsToPasson = u.getPoints();
                        customerScreen d = new customerScreen();
                        d.start(primaryStage);
                    }
                }
            }
        });
        VBox root = new VBox();

        root.getChildren().add(wel);
        root.getChildren().add(u);
        root.getChildren().add(p);
        root.getChildren().add(btn);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(15);

        Scene scene = new Scene(root, 500, 350);
        primaryStage.setWidth(500);
        primaryStage.setHeight(350);
        primaryStage.setTitle("Log In to Bookstore");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
