package lab1;

/**
 *
 * @author Jacob
 */
public class Ticket {
    Passenger passenger;
    Flight flight;
    double price;
    public Ticket(Passenger passenger, Flight flight, double price) {
        this.passenger = passenger;
        this.flight = flight;
        this.price = price;
    }
    
    public Passenger getPassenger() {
        return passenger;
    }
    
    public Flight getFlight() {
        return flight;
    }
    
    public double getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        String name = passenger.getName();
        int flightNumber = flight.getFlightNumber();
        String origin = flight.getOrigin();
        String destination = flight.getDestination();
        String departureTime = flight.getDepartureTime();
        double originalPrice = flight.getOriginalPrice();
        return name + ", Flight " + flightNumber + ", " + origin + " to " + destination + ", " + departureTime
                + ", original price: $" + originalPrice + ", ticket price: $" + price;
    }
}
