
public class Flight {
    private static String flight;
    private int seats;
    
    public Flight(String flight, int seats)
    {
        this.flight = flight;
        this.seats = seats;
    }
 
    
    public static String getFlightID(){
        return flight;
    }
    
    @Override
    public String toString(){
        return flight + " seats: " + Integer.toString(seats);
    }
}
