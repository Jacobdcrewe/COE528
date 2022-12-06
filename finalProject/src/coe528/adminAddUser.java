/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Jacob
 */
public class adminAddUser extends Application {

    //table to display
    private final TableView table = new TableView();

    //array list of users of type user
    ArrayList<User> users = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        //creating file and instance of record
        userRecord r = userRecord.getInstance();

        //creating title and size of window
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Bookstore App");
        primaryStage.setWidth(440);
        primaryStage.setHeight(520);

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

        //allow edits to the table
        table.setEditable(true);

        //creating columns for table
        TableColumn userCol = new TableColumn("Username");
        TableColumn passCol = new TableColumn("Password");
        TableColumn pntCol = new TableColumn("Points");

        //setting width of table
        userCol.setMinWidth(150);
        passCol.setMinWidth(150);
        pntCol.setMinWidth(100);

        //giving the ability to add items to the columns
        userCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        passCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        pntCol.setCellValueFactory(new PropertyValueFactory<>("points"));


        //adding columns to table
        table.getColumns().addAll(userCol, passCol, pntCol);

        //adding arraylist to the columns
        for (User user : users) {
            table.getItems().addAll(user);
        }

        //creating add user and add pass word text fields and setting the width to match the column widths
        TextField addUser = new TextField();
        addUser.setPromptText("Username");
        addUser.setMinWidth(userCol.getPrefWidth());
        TextField addPass = new TextField();
        addPass.setMinWidth(passCol.getPrefWidth());
        addPass.setPromptText("Password");

        //creating buttons
        Button btAdd = new Button("Add");
        Button btDel = new Button("Delete");
        Button btBack = new Button("Back");

        //setting add button width to match the final column
        btAdd.setMinWidth(pntCol.getPrefWidth());

        btAdd.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                //checks if both text fields have writing in them
                if (addUser.getText().trim().isEmpty() || addPass.getText().trim().isEmpty()) {
                    System.out.println("Please fill in both text fields");
                } else {
                    //checking to see if username is same as another in the system
                    boolean add = true;
                    for (User user : users) {
                        if (user.getUsername().equals(addUser.getText()) || addUser.getText().equals("admin") || addUser.getText().contains(" ")) {
                            add = false;
                            break;
                        }

                    }
                    if (add) {
                        //adds the new user to the users arraylist, the table, and writes the new user to the file
                        users.add(new User(addUser.getText(), addPass.getText()));
                        table.getItems().addAll(users.get(users.size() - 1));
                        r.write("\n" + addUser.getText() + " " + addPass.getText() + " " + users.get(users.size() - 1).getPoints());

                        //clears the textfields
                        addUser.clear();
                        addPass.clear();
                    } else {
                        System.out.println("Username is already used or contains an illegal character");
                    }

                }
            }
        }
        );

        btDel.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int index = table.getSelectionModel().getSelectedIndex();
                
                //stackoverflow help on how to get string from selected spot in table
                String first = (String) table.getVisibleLeafColumn(0).getCellData(index);
                
                //unefficient ik but it works. delete all items in teh file then rewrite the arraylist to the file
                //delete items in user record
                try {
                    try (FileWriter writer = new FileWriter(r.filename)) {
                        writer.write("");
                        writer.close();
                    }
                } catch (IOException d) {
                    System.out.println("A read error occurred.");
                }
                //remove user selected in the table and remove them from teh arraylist
                for(int i = 0; i < users.size(); i++) {
                    //if the username matches the username selected theyll get removed
                    if(users.get(i).getUsername().equals(first)) {
                        users.remove(i);
                        break;
                    }
                }
                //rewrite the user record file with the new arraylist
                for (User user : users) {
                    r.write("\n" + user.getUsername() + " " + user.getPassword() + " " + user.getPoints());
                }

                //remove the selected item from teh table
                table.getItems().remove(index);
            }
        }
        );
        btBack.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                AdminSelectPage d = new AdminSelectPage();
                d.start(primaryStage);
            }
        }
        );

        //horizontal box for the user prompts
        HBox prompts = new HBox();
        
        //horizontal box for the buttons on the bottom
        HBox btns = new HBox();
        
        //vertical box for teh table and the horizontal boxes
        VBox pane = new VBox();
        
        //spacing between items for the boxes
        prompts.setSpacing(10);
        btns.setSpacing(10);
        pane.setSpacing(5);
        pane.setPadding(new Insets(10, 0, 0, 10));
        
        //adding the textfields and teh add button to prompt box
        prompts.getChildren().addAll(addUser, addPass, btAdd);
        
        //adding delete and back button to buttons box
        btns.getChildren().addAll(btDel, btBack);
        
        //adding the table and the prompts and buttons boxes to the pane
        pane.getChildren().addAll(table, prompts, btns);

        //idk
        ((Group) scene.getRoot()).getChildren().addAll(pane);

        
        
        //setting the scene and showing it
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

}
