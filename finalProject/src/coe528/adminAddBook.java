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
 * @author justi
 */
public class adminAddBook extends Application {

    
    //creating table to display
    private final TableView table = new TableView();
    
    //creating Book array list
    ArrayList<Book> books = new ArrayList<>();
         
    @Override
    public void start(Stage primaryStage) {
        
        bookRecord r = bookRecord.getInstance();

        //trying to read teh file and writing it to the arraylist
        try {
            FileReader reader = new FileReader(r.filename);
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
        
         //allowing table to be edited 
        table.setEditable(true);

        //creating columns for table
        TableColumn bNameCol = new TableColumn<>("Book Name");
        TableColumn bPriceCol = new TableColumn<>("Book Price");
        
        //setting width of table
        bNameCol.setMinWidth(225);
        bPriceCol.setMinWidth(225);
        
         //giving the ability to add items to the columns
        bNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        bPriceCol.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));
        
        //adding columns to table
        table.getColumns().addAll(bNameCol, bPriceCol);
        
         //adding arraylist to the columns
        for (Book book : books) {
            table.getItems().addAll(book);
        }
        
        //creating add user and add pass word text fields and setting the width to match the column widths
        TextField addBName = new TextField();
        addBName.setPromptText("Name");
        addBName.setMinWidth(160);
        TextField addBPrice = new TextField();
        addBPrice.setMinWidth(160);
        addBPrice.setPromptText("Price");

        //creating buttons
        Button btAdd = new Button("Add");
        Button btDel = new Button("Delete");
        Button btBack = new Button("Back");
        
        btAdd.setMinWidth(125);
        btDel.setMinWidth(125);
        
        
                                                                                        
    //    double a = 1.0;
        
        btAdd.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                //checks if both text fields have writing in them
                if (addBName.getText().trim().isEmpty() || addBPrice.getText().trim().isEmpty()) {
                    System.out.println("Please fill in both text fields");
                } else {
                    //checking to see if books contains seperator
                    double price = Double.parseDouble(addBPrice.getText());  
                    boolean add = true;
                    for (Book book : books) {
                        if (book.getBookName().contains(" / ") || book.getBookName().equals(addBName.getText())) {
                            add = false;
                            break;
                        }

                    }
                    if (add) {
                        //adds the new user to the users arraylist, the table, and writes the new user to the file
                        books.add(new Book(addBName.getText(), price));                                                                                 //*problem here 
                        table.getItems().addAll(books.get(books.size()-1));
                        r.write("\n" + addBName.getText() + " / " + addBPrice.getText());

                        //clears the textfields
                        addBName.clear();
                        addBPrice.clear();
                    } else {
                        System.out.println("Book title cannot contain that character or this book is already in the system.");
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
                String output = (String) table.getVisibleLeafColumn(0).getCellData(index);

                //unefficient ik but it works. delete all items in teh file then rewrite the arraylist to the file
                //delete items in book record
                try {
                    try (FileWriter writer = new FileWriter(r.filename)) {
                        writer.write("");
                        writer.close();
                    }
                } catch (IOException d) {
                    System.out.println("A read error occurred.");
                }
                //remove user selected in the table and remove them from teh arraylist
                for(int i = 0; i < books.size(); i++) {
                    //if the username matches the username selected theyll get removed
                    if(books.get(i).getBookName().equals(output)) {
                        books.remove(i);
                        break;
                    }
                }
                //rewrite the user record file with the new arraylist
                for (Book book : books) {
                    r.write("\n" + book.getBookName() + " / " + book.getBookPrice());
                }

                //remove the selected item from the table
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
        
        
        //Removing extra collumn and distributing between 2 collumns 
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
   
      //horizontal box for the user prompts
        HBox prompts = new HBox();
        
        //horizontal box for the buttons on the bottom
        HBox btns = new HBox();
        
        //vertical box for teh table and the horizontal boxes
        VBox pane = new VBox();
        
        //spacing between items for the boxes
        prompts.setSpacing(10);
        btns.setSpacing(300);
        pane.setSpacing(5);
        pane.setPadding(new Insets(10, 0, 0, 10));
        
        
        
        
        //adding the textfields and teh add button to prompt box
        prompts.getChildren().addAll(addBName, addBPrice, btAdd);
        
        //adding delete and back button to buttons box
        btns.getChildren().addAll(btBack, btDel);
        
        //adding the table and the prompts and buttons boxes to the pane
        pane.getChildren().addAll(table, prompts, btns);

        //idk             
        
        
        
        Scene scene = new Scene(new Group());
        primaryStage.setWidth(500);
        primaryStage.setHeight(520);
        primaryStage.setTitle("Bookstore App");
        primaryStage.setScene(scene);
        primaryStage.show();  
        
        ((Group) scene.getRoot()).getChildren().addAll(pane);

        
    }

        
}
