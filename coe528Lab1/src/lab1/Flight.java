
package lab1;

/**
 *
 * @author Jacob
 */
public class Flight {
    private int flightNumber, capacity, numberOfSeatsLeft;
    private String origin, destination, departureTime;
    private double originalPrice;
    
    public Flight (int flightNumber, String origin, String destination, String departureTime, int capacity, double originalPrice) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.capacity = capacity;
        this.originalPrice = originalPrice;
        numberOfSeatsLeft = capacity;
        
        if(origin.equals(destination)) {
            throw new IllegalArgumentException();
        }
    }
    //getters
    public int getFlightNumber() {
        return flightNumber;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public String getDestination() {
        return destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public double getOriginalPrice() {
        return originalPrice;
    }
    
    public int getNumberOfSeatsLeft() {
        return numberOfSeatsLeft;
    }
    
    //setters
    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }
    
    //book a seat method. this decrements the number of seats left if the number of seats is greater than 0 and then returns true otherwise returns false
    public boolean bookASeat() {
        if(numberOfSeatsLeft > 0) {
            numberOfSeatsLeft --;
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return "Flight " + flightNumber + ", " + origin + " to " + destination + ", " + departureTime + ", original price: $" + originalPrice;
    }
}
