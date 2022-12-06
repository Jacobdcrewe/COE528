/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Jacob
 */
public class userRecord {
    // Name of the associated file     
    public String filename;
    private static userRecord instance;
    private FileWriter file;
    
    //creating file
    private userRecord() {
        try {
            file = new FileWriter(filename, true);
            file.close();
        } catch (IOException e) {
            System.out.println("A file creation error occurred.");
        }
        
    }
    
    //setting filename
    public userRecord(String n) {
        filename = n;
    }
    
    //if the file exists do nothing if it doesnt create teh file
    public static userRecord getInstance() {
        if (instance == null) {
            instance = new userRecord("customers.txt");
        }

        return instance;
    }

    //reading the file
    public void read() {

        try {
            FileReader reader = new FileReader(filename);
            Scanner scan = new Scanner(reader);
            //run while loop if there is a next line then printing the next line
            while(scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }
        } catch (IOException e) {
            System.out.println("A read error occurred.");
        }
    }
     
    //writing to the file
    public void write(String msg) {
        try {
            try (
                    FileWriter fw = new FileWriter(filename,true)) {
                fw.write(msg);
            }
        } catch (IOException e) {
            System.out.println("A write error occurred.");
        }

        
    }
}
