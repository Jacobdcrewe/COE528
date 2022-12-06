package lab1;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jacob
 */
public class Manager {

    ArrayList<Flight> flights;
    ArrayList<Ticket> tickets;

    public Manager() {
        flights = new ArrayList<>();
        tickets = new ArrayList<>();
    }

    public void createFlights() {
        Scanner userIn = new Scanner(System.in);
        String scanString;
        int flightNumber = 0, capacity = 0;
        String origin, destination, departureTime;
        double originalPrice = 0;
        System.out.println("Adding a new flight.");
        System.out.println("Flight Number: ");
        scanString = userIn.nextLine();
        try {
            flightNumber = Integer.parseInt(scanString);
        } catch (NumberFormatException e) {
            System.out.println("Flight Number not valid");
            createFlights();
        }

        System.out.println("Origin: ");
        scanString = userIn.nextLine();
        origin = scanString;
        System.out.println("Destination: ");
        scanString = userIn.nextLine();
        destination = scanString;
        System.out.println("Departure Time: ");
        scanString = userIn.nextLine();
        departureTime = scanString;

        System.out.println("Capacity: ");
        scanString = userIn.nextLine();
        try {
            capacity = Integer.parseInt(scanString);
        } catch (NumberFormatException e) {
            System.out.println("Capacity not valid");
            createFlights();
        }

        System.out.println("Original Price: ");
        scanString = userIn.nextLine();
        try {
            originalPrice = Double.valueOf(scanString);
        } catch (NumberFormatException e) {
            System.out.println("Original Price not valid");
            createFlights();
        }
        Flight f = new Flight(flightNumber, origin, destination, departureTime, capacity, originalPrice);
        flights.add(f);
        System.out.println("This flight has been added: ");
        System.out.println(f);
    }

    public void displayAvailableFlights(String origin, String destination) {
        boolean flightsAvailable = false;
        for (int i = 0; i <= flights.size() - 1; i++) {
            if (flights.get(i).getOrigin().equals(origin) && flights.get(i).getDestination().equals(destination) && flights.get(i).getNumberOfSeatsLeft() > 0) {
                if (flightsAvailable == false) {
                    System.out.println("Flights available: ");
                    flightsAvailable = true;
                }
                System.out.println(flights.get(i));
            }

        }
        if (flightsAvailable == false) {
            System.out.println("No flights are available.");
        }
    }

    public Flight getFlight(int flightNumber) {
        for (int i = 0; i <= flights.size() - 1; i++) {
            if (flights.get(i).getFlightNumber() == flightNumber) {
                return flights.get(i);
            }
        }
        return null;
    }

    public void bookSeat(int flightNumber, Passenger p) {
        int index = 0;
        boolean flightAvailable = false;
        double ticketPrice;
        for (int i = 0; i <= flights.size() - 1; i++) {
            if (flights.get(i).getFlightNumber() == flightNumber) {
                index = i;
                flightAvailable = true;
            }
        }
        if (flightAvailable && flights.get(index).getNumberOfSeatsLeft() > 0) {
            flights.get(index).bookASeat();
            ticketPrice = p.applyDiscount(flights.get(index).getOriginalPrice());
            Ticket ticket = new Ticket(p, flights.get(index), ticketPrice);
            tickets.add(ticket);
            System.out.println("The ticket for flight number " + flights.get(index).getFlightNumber() + " has been booked with ticket " + ticket);
        } else if (flightAvailable == false && flights.get(index).getNumberOfSeatsLeft() > 0) {
            System.out.println("This flight number does not exist");
        } else if (flightAvailable && flights.get(index).getNumberOfSeatsLeft() < 0) {
            System.out.println("There are no more seats left on the plane");
        } else {
            System.out.println("Error");
        }

    }

    public static void main(String[] args) {
        Manager M = new Manager();
        System.out.println("Welcome to the flight management system.");
        Scanner userIn = new Scanner(System.in);
        String scanString;
        OUTER:
        while (true) {
            System.out.println("Type 'flight' to add a flight");
            System.out.println("Type 'plan' to see available flights on a specific route");
            System.out.println("Type 'number' to see info about a flight based on the flight number");
            System.out.println("Type 'book' to book a ticket for a flight");
            System.out.println("Type 'end' to end program");
            System.out.println("Input: ");
            scanString = userIn.nextLine();
            switch (scanString) {
                case "flight":
                    M.createFlights();
                    break;
                case "plan":
                    String origin,
                     destination;
                    System.out.println("Origin: ");
                    origin = userIn.nextLine();
                    System.out.println("Destination: ");
                    destination = userIn.nextLine();
                    M.displayAvailableFlights(origin, destination);
                    break;
                case "number": {
                    int flightNumber;
                    System.out.println("Flight number: ");
                    scanString = userIn.nextLine();
                    try {
                        flightNumber = Integer.parseInt(scanString);
                        if (M.getFlight(flightNumber) == null) {
                            System.out.println("Flight does not exist");
                        } else {
                            System.out.println(M.getFlight(flightNumber));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please input an integer value");
                    }
                    break;
                }
                case "book": {
                    int flightNumber = 0, age, yearsMember;
                    String name;
                    Passenger p = null;
                    System.out.println("Passengers name: ");
                    name = userIn.nextLine();
                    System.out.println("Passengers age: ");
                    scanString = userIn.nextLine();
                    try {
                        age = Integer.parseInt(scanString);
                    } catch (NumberFormatException e) {
                        System.out.println("Please input an integer value");
                        break;
                    }
                    System.out.println("Is the passenger a member (yes/no):");
                    scanString = userIn.nextLine();
                    if (scanString.equals("yes")) {
                        System.out.println("Number of years they've been a member: ");
                        scanString = userIn.nextLine();
                        try {
                            yearsMember = Integer.parseInt(scanString);
                        } catch (NumberFormatException e) {
                            System.out.println("Please input an integer value");
                            break;
                        }
                        p = new Member(name, age, yearsMember);
                    } else if (scanString.equals("no")) {
                        p = new NonMember(name, age);
                    } else {
                        System.out.println("Please input yes or no");
                    }
                    System.out.println("Flight number: ");
                    scanString = userIn.nextLine();
                    try {
                        if (M.getFlight(Integer.parseInt(scanString)) == null) {
                            System.out.println("Flight does not exist");
                            break;
                        } else {
                            flightNumber = Integer.parseInt(scanString);
                        }
                        M.bookSeat(flightNumber, p);
                    } catch (NumberFormatException e) {
                        System.out.println("Please input an integer value");
                        break;
                    }
                    break;
                }
                case "end":
                    break OUTER;
                default:
                    System.out.println("Please input one of the options");
                    break;
            }
        }
    }
}
