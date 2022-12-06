package lab4;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author Jacob
 */
public class FoodCategory extends FoodComponent{
    private ArrayList<FoodComponent> components;

    public FoodCategory(String category) {
        components = new ArrayList<FoodComponent>();
        super.category = category;
    }
    
    public void print(int level) {
        for(int i = 0; i<level; i++) {
            System.out.print("\t");
        }
        double cost = super.price;
        for(int i = 0; i<components.size(); i++) {
            cost = cost + components.get(i).getPrice();
        }
        System.out.print("FoodCategory: (" + super.category + ", " + cost + ") contains:\n");
        level++;
        for(FoodComponent c: components) {
            
            c.print(level);
        }
    }
    
    @Override
    public void add(FoodComponent c) {
        components.add(c);
    }

    @Override
    public void remove(FoodComponent c) {
        components.remove(c);
        
    }

    @Override
    public double getPrice() {
        double sum = 0;
        for(FoodComponent c: components){
            sum = sum + c.getPrice();
        }
        
        
        return sum;
    }
}
