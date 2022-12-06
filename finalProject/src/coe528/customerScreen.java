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
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Jacob
 */
public class customerScreen extends Application {

    static double totalCost;
    //table to display
    private final TableView table = new TableView();

    //array list of books of type book
    ArrayList<Book> books = new ArrayList<>();

    //array list of users of type user
    ArrayList<User> users = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        //creating file and instance of record
        userRecord u = userRecord.getInstance();
        bookRecord b = bookRecord.getInstance();

        //creating title and size of window
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Bookstore App");
        primaryStage.setWidth(440);
        primaryStage.setHeight(540);

        //trying to read teh file and writing it to the arraylist
        try {
            FileReader reader = new FileReader(b.filename);
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String object = scan.nextLine();
                if (object.length() > 0) {
                    String[] str = object.split(" / ");
                    books.add(new Book(str[0], Double.parseDouble(str[1])));
                }
            }
        } catch (IOException e) {
            System.out.println("A read error occurred.");
        }

        try {
            FileReader reader = new FileReader(u.filename);
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
        //welcome label with points
        String status;
        Label heading = new Label();
        if (LoginScreen.pointsToPasson < 1000) {
            status = "silver";
        } else {
            status = "gold";
        }

        heading.setText("Welcome " + LoginScreen.usernameToPasson + ". You have " + LoginScreen.pointsToPasson + " points. Your status is " + status + ".");
        //allow edits to the table
        table.setEditable(true);

        //creating columns for table
        TableColumn bookCol = new TableColumn("Book Name");
        TableColumn priceCol = new TableColumn("Book Price");
        TableColumn selectCol = new TableColumn("Select");

        //setting width of table
        bookCol.setMinWidth(150);
        priceCol.setMinWidth(150);
        selectCol.setMinWidth(100);

        //disabling sort bc its messes up program
        bookCol.setSortable(false);
        priceCol.setSortable(false);
        selectCol.setSortable(false);

        //giving the ability to add items to the columns
        bookCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));
        selectCol.setCellValueFactory(new PropertyValueFactory<>("select"));

        //adding columns to table
        table.getColumns().addAll(bookCol, priceCol, selectCol);

        //disabling selection in table
        table.setSelectionModel(null);

        //centering select column
        selectCol.setStyle("-fx-alignment: CENTER;");

        //adding arraylist to the columns
        for (Book book : books) {
            table.getItems().addAll(book);

        }

        //creating buttons
        Button btBuy = new Button("Buy");
        Button btRdm = new Button("Redeem points and Buy");
        Button btLogout = new Button("Logout");

        btBuy.setMinWidth(90);
        btRdm.setMinWidth(200);
        btLogout.setMinWidth(90);
        btBuy.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                customerScreen.totalCost = 0;
                ArrayList<Book> selectedBooks = new ArrayList<>();
                for (Book book : books) {
                    if (book.getSelect().isSelected()) {
                        customerScreen.totalCost += book.getBookPrice();
                        selectedBooks.add(book);
                    }

                }
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUsername().equals(LoginScreen.usernameToPasson)) {
                        LoginScreen.pointsToPasson += (int) (customerScreen.totalCost * 10);
                        users.get(i).setPoints(LoginScreen.pointsToPasson);
                        break;
                    }
                }
                //unefficient ik but it works. delete all items in teh file then rewrite the arraylist to the file
                //delete items in user record
                try {
                    try (FileWriter writer = new FileWriter(u.filename)) {
                        writer.write("");
                        writer.close();
                    }
                } catch (IOException d) {
                    System.out.println("A read error occurred.");
                }
                //rewrite the user record file with the new arraylist
                for (User user : users) {
                    u.write("\n" + user.getUsername() + " " + user.getPassword() + " " + user.getPoints());
                }

                try {
                    try (FileWriter writer = new FileWriter(b.filename)) {
                        writer.write("");
                        writer.close();
                    }
                } catch (IOException d) {
                    System.out.println("A read error occurred.");
                }
                //remove user selected in the table and remove them from teh arraylist
                for (int i = 0; i < books.size(); i++) {
                    for (Book select : selectedBooks) {
                        //if the username matches the username selected theyll get removed
                        if (books.get(i).getBookName().equals(select.getBookName())) {
                            books.remove(i);
                        }

                    }

                }
                //rewrite the user record file with the new arraylist
                for (Book book : books) {
                    b.write("\n" + book.getBookName() + " / " + book.getBookPrice());
                }

                customerCostScreen d = new customerCostScreen();
                d.start(primaryStage);
            }
        }
        );

        btRdm.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ArrayList<Book> selectedBooks = new ArrayList<>();
                customerScreen.totalCost = 0;
                for (Book book : books) {
                    if (book.getSelect().isSelected()) {
                        customerScreen.totalCost += book.getBookPrice();
                        selectedBooks.add(book);
                    }

                }
                if (LoginScreen.pointsToPasson >= 0) {

                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getUsername().equals(LoginScreen.usernameToPasson)) {
                            if ((customerScreen.totalCost - (((double) LoginScreen.pointsToPasson) / 100)) >= 0) {
                                customerScreen.totalCost = customerScreen.totalCost - ((double) (LoginScreen.pointsToPasson) / 100);;
                                LoginScreen.pointsToPasson = (int) (customerScreen.totalCost * 10);
                                System.out.println(LoginScreen.pointsToPasson);
                                users.get(i).setPoints(LoginScreen.pointsToPasson);
                                break;
                            } else {
                                users.get(i).setPoints((int) (users.get(i).getPoints() - (customerScreen.totalCost * 100)));
                                LoginScreen.pointsToPasson = users.get(i).getPoints();
                                customerScreen.totalCost = 0;
                                break;

                            }

                        }
                    }

                    //unefficient ik but it works. delete all items in teh file then rewrite the arraylist to the file
                    //delete items in user record
                    try {
                        try (FileWriter writer = new FileWriter(u.filename)) {
                            writer.write("");
                            writer.close();
                        }
                    } catch (IOException d) {
                        System.out.println("A read error occurred.");
                    }
                    //rewrite the user record file with the new arraylist
                    for (User user : users) {
                        u.write("\n" + user.getUsername() + " " + user.getPassword() + " " + user.getPoints());
                    }

                }

                try {
                    try (FileWriter writer = new FileWriter(b.filename)) {
                        writer.write("");
                        writer.close();
                    }
                } catch (IOException d) {
                    System.out.println("A read error occurred.");
                }
                //remove user selected in the table and remove them from teh arraylist
                for (int i = 0; i < books.size(); i++) {
                    for (Book select : selectedBooks) {
                        //if the username matches the username selected theyll get removed
                        if (books.get(i).getBookName().equals(select.getBookName())) {
                            books.remove(i);
                        }

                    }

                }
                //rewrite the user record file with the new arraylist
                for (Book book : books) {
                    b.write("\n" + book.getBookName() + " / " + book.getBookPrice());
                }

                customerCostScreen d = new customerCostScreen();
                d.start(primaryStage);

            }
        }
        );
        btLogout.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                LoginScreen d = new LoginScreen();
                d.start(primaryStage);
            }
        }
        );

        //horizontal box for the buttons on the bottom
        HBox btns = new HBox();

        //vertical box for teh table and the horizontal boxes
        VBox pane = new VBox();

        //spacing between items for the boxes
        btns.setSpacing(10);
        pane.setSpacing(5);
        pane.setPadding(new Insets(10, 0, 0, 10));

        //adding delete and back button to buttons box
        btns.getChildren().addAll(btBuy, btRdm, btLogout);

        //adding the table and the prompts and buttons boxes to the pane
        pane.getChildren().addAll(heading, table, btns);

        //idk
        ((Group) scene.getRoot()).getChildren().addAll(pane);

        //setting the scene and showing it
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
