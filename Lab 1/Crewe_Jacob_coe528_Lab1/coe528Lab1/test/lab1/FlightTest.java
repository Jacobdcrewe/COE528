/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import static org.hamcrest.CoreMatchers.is;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jacob
 */
public class FlightTest {
    
    public FlightTest() {
    }
    
    Flight f, g = new Flight(1, "Canada", "Mexico", "01/20/22 2:56 pm", 2, 3.59);
    @Test
    public void testConstructor() {
        System.out.println("Testing constructors");
        f = new Flight(1030, "Toronto", "Kolkata", "03/02/99 7:50 pm", 2, 1000);
        assertThat("Flight 1030, Toronto to Kolkata, 03/02/99 7:50 pm, original price: $1000.0", is(f.toString()));
        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConstructor() {
        System.out.println("Testing invalid constructors");
        f = new Flight(1, "Canada", "Canada", "01/20/22 2:56 pm", 2, 3.59);
    }
    /**
     * Test of getFlightNumber method, of class Flight.
     */
    @Test
    public void testGetFlightNumber() {
        System.out.println("Testing getFlightNumber method");
        int expResult = 1;
        int result = g.getFlightNumber();
        if(expResult != result) {
            fail("Expected result was not the same as the result");
        }
    }

    /**
     * Test of getOrigin method, of class Flight.
     */
    @Test
    public void testGetOrigin() {
        System.out.println("Testing getOrigin method");
        String expResult = "Canada";
        String result = g.getOrigin();
        if(!expResult.equals(result)) {
            fail("Expected result was not the same as the result");
        }
    }

    /**
     * Test of getDestination method, of class Flight.
     */
    @Test
    public void testGetDestination() {
        System.out.println("Testing getDestination method");
        String expResult = "Mexico";
        String result = g.getDestination();
        if(!expResult.equals(result)) {
            fail("Expected result was not the same as the result");
        }
    }

    /**
     * Test of getDepartureTime method, of class Flight.
     */
    @Test
    public void testGetDepartureTime() {
        System.out.println("Testing getDepartureTime method");
        String expResult = "01/20/22 2:56 pm";
        String result = g.getDepartureTime();
        if(!expResult.equals(result)) {
            fail("Expected result was not the same as the result");
        }
    }

    /**
     * Test of getCapacity method, of class Flight.
     */
    @Test
    public void testGetCapacity() {
        System.out.println("Testing getCapacity method");
        int expResult = 2;
        int result = g.getCapacity();
        if(expResult != result) {
            fail("Expected result was not the same as the result");
        }
    }

    /**
     * Test of getOriginalPrice method, of class Flight.
     */
    @Test
    public void testGetOriginalPrice() {
        System.out.println("Testing getOriginalPrice method");
        double expResult = 3.59;
        double result = g.getOriginalPrice();
        if(expResult != result) {
            fail("Expected result was not the same as the result");
        }
    }

    /**
     * Test of getNumberOfSeatsLeft method, of class Flight.
     */
    @Test
    public void testGetNumberOfSeatsLeft() {
        System.out.println("Testing getNumberOfSeatsLeft method");
        int expResult = 2;
        int result = g.getNumberOfSeatsLeft();
        if(expResult != result) {
            fail("Expected result was not the same as the result");
        }
    }

    /**
     * Test of setFlightNumber method, of class Flight.
     */
    @Test
    public void testSetFlightNumber() {
        System.out.println("Testing setFlightNumber method");
        int flightNumber = 3;
        g.setFlightNumber(flightNumber);
        if(g.getFlightNumber() != flightNumber) {
            fail("Expected result was not the same as the result");
        }
    }

    /**
     * Test of setOrigin method, of class Flight.
     */
    @Test
    public void testSetOrigin() {
        System.out.println("Testing setOrigin method");
        String origin = "Toronto";
        g.setOrigin(origin);
        if(!origin.equals(g.getOrigin())) {
            fail("Expected result was not the same as the result");
        }
    }

    /**
     * Test of setDestination method, of class Flight.
     */
    @Test
    public void testSetDestination() {
        System.out.println("Testing setDestination method");
        String destination = "New York City";
        g.setDestination(destination);
        if(!destination.equals(g.getDestination())) {
            fail("Expected result was not the same as the result");
        }
    }

    /**
     * Test of setDepartureTime method, of class Flight.
     */
    @Test
    public void testSetDepartureTime() {
        System.out.println("Testing setDepartureTime method");
        String departureTime = "03/02/99 7:50 pm";
        g.setDepartureTime(departureTime);
        if(!departureTime.equals(g.getDepartureTime())) {
            fail("Expected result was not the same as the result");
        }
    }

    /**
     * Test of setCapacity method, of class Flight.
     */
    @Test
    public void testSetCapacity() {
        System.out.println("Testing setCapacity method");
        int capacity = 105;
        g.setCapacity(capacity);
        if(g.getCapacity() != capacity) {
            fail("Expected result was not the same as the result");
        }
    }

    /**
     * Test of setOriginalPrice method, of class Flight.
     */
    @Test
    public void testSetOriginalPrice() {
        System.out.println("Testing setOriginalPrice method");
        double originalPrice = 7.64;
        g.setOriginalPrice(originalPrice);
        if(g.getOriginalPrice() != originalPrice) {
            fail("Expected result was not the same as the result");
        }
    }

    /**
     * Test of bookASeat method, of class Flight.
     */
    @Test
    public void testBookASeat() {
        System.out.println("Testing bookASeat method");
        boolean expResult = true;
        boolean result = g.bookASeat();
        if(!(expResult && result)) {
            fail("Expected result was not the same as the result");
        }
    }

    /**
     * Test of toString method, of class Flight.
     */
    @Test
    public void testToString() {
        System.out.println("Testing toString Method");
        Flight h = new Flight(354, "Calgary", "Vancouver", "11/03/10 4:36 pm", 1609, 739.93);
        String expected = "Flight 354, Calgary to Vancouver, 11/03/10 4:36 pm, original price: $739.93";
        String result = h.toString();
        assertEquals(expected,result);
    }
    
}
