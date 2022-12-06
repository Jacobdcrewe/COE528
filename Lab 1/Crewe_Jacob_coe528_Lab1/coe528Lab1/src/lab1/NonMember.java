
package lab1;

/**
 *
 * @author Jacob
 */
public class NonMember extends Passenger{

    public NonMember(String name, int age) {
        super(name, age);
    }

    @Override
    double applyDiscount(double p) {
        double price;
        if(this.age > 65) {
            price = p*0.9;
        } else {
            price = p;
        }
        return price;
    }
    
}
