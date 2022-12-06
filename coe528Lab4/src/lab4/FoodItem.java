package lab4;



/**
 *
 * @author Jacob
 */
public class FoodItem extends FoodComponent{
    
    FoodItem(String food, double price) {
        super.food = food;
        super.price = price;
    }
    @Override
    public void print(int level) {
        for(int i = 0; i<level; i++) {
            System.out.print("\t");
        }
        System.out.println("FoodItem: " + super.food + ", " + price);
        
    }
    
    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void add(FoodComponent c) {}

    @Override
    public void remove(FoodComponent c) {}
}
