package lab3;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueueOfDistinctStrings {
    // Overview: QueueOfDistinctStrings are mutable, bounded         
    // collection of distinct strings that operate in      
    // FIFO (First-In-First-Out) order.      
    //     
    // The abstraction function is:     
    // a) Write the abstraction function here 
    /* 
       AF(c) = an abstract queue d      (where c is a Java QueueOfDistinctStrings object)
               where d.front = c.items.get(0)
                       d.end = c.items.get(c.items.size() - 1) where the index of the last element is the size of the arraylist minus 1
                d.collection = c.items      
    */
    //implementation in the toString method

    // The rep invariant is:
    // b) Write the rep invariant here 
    /*
       RI(c) = true if frequencyOfItems = 1  where frequencyOfItems is the number of times an element in the arraylist occurs
             = false otherwise 
    */
    //implementation in repOk() method
    
    
    //the rep 
    private ArrayList<String> items;
    // constructor     

    public QueueOfDistinctStrings() {
        // EFFECTS: Creates a new QueueOfDistinctStrings object         
        items = new ArrayList<String>();
    }
    // MODIFIES: this     
    // EFFECTS: Appends the element at the end of the queue      
    //          if the element is not in the queue, otherwise     
    //          does nothing.     

    public void enqueue(String element) throws Exception {
        if (element == null) {
            throw new Exception();
        }
        if (false == items.contains(element)) {
            items.add(element);
        }
    }

    public String dequeue() throws Exception {
        // MODIFIES: this         
        // EFFECTS: Removes an element from the front of the queue          
        if (items.size() == 0) {
            throw new Exception();
        }
        return items.remove(0);
    }

    public boolean repOK() {
        // EFFECTS: Returns true if the rep invariant holds for this            
        //          object; otherwise returns false            
        // c) Write the code for the repOK() here 
        int frequencyOfItems = 1;
        for(int i=0; i<items.size(); i++) {
            for(int j=i+1; j<items.size(); j++) {
                if(items.get(i).equals(items.get(j))) {
                    frequencyOfItems++;
                }
            }
        }
        if(frequencyOfItems == 1) {
            return true;
        } else {
            return false;
        }
        
    }

    public String toString() {
        // EFFECTS: Returns a string that contains the strings in the          
        //          queue, the front element and the end element.          
        //          Implements the abstraction function.           
        // d) Write the code for the toString() here    
        String output;
        output = items.toString();
        return "Collection: " + output + ", Front: " + items.get(0) + ", End: " + items.get(items.size()-1);
    }
    
    //a test main class
    /*
    public static void main(String[] args) {
        QueueOfDistinctStrings Q = new QueueOfDistinctStrings();
        try {
            Q.enqueue("ab");
            Q.enqueue("cd");
            Q.enqueue("ae");
        } catch (Exception ex) {
            Logger.getLogger(QueueOfDistinctStrings.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Q.items.add("bd");
        System.out.println(Q.repOK());
        System.out.println(Q.toString());
    } 
    */
}
