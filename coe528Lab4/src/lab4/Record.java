package lab4;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Jacob
 */
public class Record {

    // Name of the associated file     
    private String filename;
    private static Record instance;
    private FileWriter file;
    
    private Record() {
        try {
            file = new FileWriter("src\\lab4\\"+ filename, true);
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        
    }
    
    public Record(String n) {
        filename = n;
    }
    
    public static Record getInstance() {
        if (instance == null) {
            instance = new Record("record.txt");
        }

        return instance;
    }

    
// Effects: Reads and prints the contents of the associated      
// file to the standard output.       

    public void read() {

        try {
            // Write the code here 
            FileReader reader = new FileReader("src\\lab4\\" + filename);
            Scanner scan = new Scanner(reader);
            while(scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Effects: Appends the specified message, msg, to the      
    // associated file.     
    public void write(String msg) {
        try {
            // Write the code here
            FileWriter fw = new FileWriter("src\\lab4\\" + filename,true);
            fw.write(msg);
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        
    }


    public static void main(String[] args) {
        // Fill the blank below that obtains the sole instance          
        // of the Record class.          
        // (You should not invoke the Record constructor here.)
        Record r = Record.getInstance();
        
        
        // Do not modify the code below 
        r.write("Hello-1\n");
        r.write("Hello-2\n");
        System.out.println("Currently the file record.txt " + "contains the following lines:");
        r.read();
        
    }
}
