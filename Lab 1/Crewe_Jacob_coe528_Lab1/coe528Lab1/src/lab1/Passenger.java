package lab1;

/**
 *
 * @author Jacob
 */
public abstract class Passenger {
    protected String name;
    protected int age;
    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    abstract double applyDiscount(double p);
}
