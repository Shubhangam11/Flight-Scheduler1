
import java.sql.*;
import java.util.Calendar;


public class Booking {
    private static String dbURL = "jdbc:derby://localhost:1527/FlightSchedulerDBShubhangamDuttaSZD77";
    private static String username = "java";
    private static String password = "java";
 
    private Connection connection = null;
    private PreparedStatement book = null;
    private PreparedStatement waitList = null;
    private PreparedStatement pass = null;
    private PreparedStatement w_pass = null;
    private PreparedStatement okay = null;
    private PreparedStatement getFlightSeats = null;
    private PreparedStatement addingDate = null;
    private PreparedStatement WaitlistCheck = null;
    private PreparedStatement getWaitList_now = null;
    private PreparedStatement fine = null;
    private PreparedStatement waitlistFeedback = null;

    
    
    
    public Booking(){
        try{
            connection = DriverManager.getConnection(dbURL, username, password);
        }
        catch (SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    
    public void ConnectionToDB(){
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            
            connection = DriverManager.getConnection(dbURL, username, password); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
    
    
    
    
    public Boolean MakeReservation(String date, String ID, String name)
    {
        if (isOkay(date, ID, name))
            return true;
        else {
            WaitlistQ(date, ID, name);
            return false;
        }       
    }
    public void WaitlistQ(String date, String ID, String name){
        
                java.sql.Timestamp CurrentTS = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());            
                try{
                ConnectionToDB();
                
                waitList = connection.prepareStatement("INSERT into WAITLIST (NAME, FLIGHT, DATE, TIMESTAMP) values (?, ?,?, ?)");
                
                waitList.setString(1, name);
                waitList.setString(2, date);
                waitList.setString(3, ID);
                waitList.setTimestamp(4, CurrentTS);
                
                waitList.executeUpdate();
            }
            catch (SQLException except){
                except.printStackTrace();
            }
            waitList = null;
    }
    public String getWaitList(String flightID, String date)
    {
        
        String out = "";
        ConnectionToDB();
        int count = 1;
        
        try {
            getWaitList_now = connection.prepareStatement("SELECT * FROM WAITLIST");
            ResultSet rs = getWaitList_now.executeQuery();
            
            while(rs.next()){
                String selectedFlight = rs.getString("FLIGHT");
                String selectedDay = rs.getString("DATE");
                String selectedName = rs.getString("NAME");
                java.sql.Timestamp TS = rs.getTimestamp("TIMESTAMP");
                
                
                    out = out.concat(count + ": " + selectedName + " booked at: [" + TS + "]\n");
                    count++;
          
            }
        }
        catch (Exception except){
                except.printStackTrace();
        } 
        getWaitList_now = null;
        return out;
    }
    
    public boolean isOkay(String date, String ID, String name)
        {
        String sql = "insert into BOOKING"
                    + "(FLIGHT,DATE,NAME)"
                    + " VALUES(?,?,?)";
        if (openSeats(date, ID)){
            try{
                ConnectionToDB();
                
                book = connection.prepareStatement(sql);
                
                book.setString(1, ID);
                book.setString(2, date);
                book.setString(3, name);  
                book.executeUpdate();

                book = null;

            }
            catch(SQLException e){
                e.printStackTrace();
            }
            return true;
        }
        else {
            return false;
        }
    }
    
    
    public Boolean openSeats(String date, String ID){
        ConnectionToDB();
        int count = 0;
        try 
        {
            okay = connection.prepareStatement("SELECT * FROM BOOKING");
            ResultSet set = okay.executeQuery();
            int realSeatsTaken = getSeatNumber(ID);
            while(set.next())
            {
                        
                String selectedFlight = set.getString("FLIGHT");
                
                if(selectedFlight.equals(ID))
                {
                    count++;
                }  
                 
            }
                
            if (realSeatsTaken <= count)
            {
                okay =null;
                return false;
            }                                                         
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }  
        okay = null;
        return true;
        
    }        

    
    public int getSeatNumber(String FLIGHT) 
    {
        ConnectionToDB();

    try {
           getFlightSeats = connection.prepareStatement("select * from FLIGHT"); 
           ResultSet rs = getFlightSeats.executeQuery();
            
            while(rs.next()){
                String selectedFlight = rs.getString("FLIGHT");
                
                if(selectedFlight.equals(FLIGHT))
                {
                    int selectedSeat = rs.getInt("SEATS");
                    return selectedSeat;
                }  
            }                 
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }        
    
        return 0;
    }
    
    public String Passenger_Info(String ID, String date){

        ConnectionToDB();
        int x = 1;
        String info = "";
        try {
            
            pass = connection.prepareStatement("SELECT * FROM BOOKING");
            ResultSet set = pass.executeQuery();
            
            while(set.next()){

                String name = set.getString("NAME");
                
                
                info += x + ") " + name + " on flight " + ID + "\n";
                x++;
                
            }
        }
        catch (Exception except){
                except.printStackTrace();
        }
        pass = null;
        return info;
    }
    
    
    public String GetPassengerStatus(String name){
        String status = "Status for " + name + "\n************************\n"+"\n************************\n";
        String booked = "Customer's Booked Flights:\n";
        String waitlisted = "Customer's Waitlisted Flights:\n";
        int count = 1;
        ConnectionToDB();
        
        try {
            pass = connection.prepareStatement("SELECT * FROM BOOKING");
            ResultSet rs = pass.executeQuery();
            
            while(rs.next()){
                String selectedFlight = rs.getString("FLIGHT");
                String selectedDay = rs.getString("DATE");
                String selectedName = rs.getString("NAME");
                
                if(selectedName.equals(name)){
                    booked = booked.concat(count + ": Flight " + selectedFlight + " on " + 
                            selectedDay + "\n");
                    count++;
                }
            }
            booked = booked.concat("\n");
            count = 1;
            
            WaitlistCheck = connection.prepareStatement("SELECT * FROM WAITLIST");
            rs = WaitlistCheck.executeQuery();
            while(rs.next()){
                String selectedFlight = rs.getString("FLIGHT");
                String selectedDate = rs.getString("DATE");
                String selectedName = rs.getString("NAME");
                java.sql.Timestamp TS = rs.getTimestamp("TIMESTAMP");
                
                if(selectedName.equals(name)){
                    waitlisted = waitlisted.concat(count + ": Flight " + selectedFlight + " on " +
                            selectedDate + ". attempted to book at [" + TS + "]\n");
                }
            }
            waitlisted = waitlisted.concat("\n");
        }
        catch (Exception except){
                except.printStackTrace();
        }
        pass = null;
        WaitlistCheck = null;
        status = status.concat(booked);
        status = status.concat(waitlisted);
        return status;
    }
    
    public void AddNewDate(String date) {
        String sql = "INSERT into date(DATE) VALUES(?)";
        try{
            ConnectionToDB();
            addingDate = connection.prepareStatement(sql);

            addingDate.setString(1, date);

            addingDate.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        addingDate = null;
    }

    public void Rebooking(String flightID){
        ConnectionToDB();
         
        try {
            pass = connection.prepareStatement("SELECT * FROM BOOKing");
            ResultSet rs = pass.executeQuery();
            
            while(rs.next()){
                String selectedFlight = rs.getString("FLIGHT");
                String selectedDate = rs.getString("DATE");
                String name = rs.getString("NAME");
                
                if(selectedFlight.equals(flightID))
                {
                    String renewedID = NewFlight(flightID, selectedDate);
                    DeleteSchedule(name, selectedDate, flightID);
                    MakeReservation(selectedDate, renewedID, name);
                }
            }
        }
        catch (Exception except){
                except.printStackTrace();
        }
        pass = null;
    }
    
    public String NewFlight(String flightID, String date){
        ConnectionToDB();
        try {
            pass = connection.prepareStatement("SELECT * FROM FLIGHT");
            ResultSet rs = pass.executeQuery();
            
            while(rs.next()){
                String selectedFlight = rs.getString("FLIGHT");
                
                if((!selectedFlight.equals(flightID)) && openSeats(date, selectedFlight)){
                    return selectedFlight;
                }
            }
        }
        catch (Exception except){
                except.printStackTrace();
        }
        pass = null;
        return null;
    }
    
    public boolean ScheduledPassengers(String flightID) {
        ConnectionToDB();
        
        try {
            pass = connection.prepareStatement("SELECT * FROM BOOKING");
            ResultSet rs = pass.executeQuery();
            
            while(rs.next()){
                String selectedFlight = rs.getString("FLIGHT");
                
                if(selectedFlight.equals(flightID)){
                    return true;
                }
            }
        }
        catch (Exception except){
                except.printStackTrace();
        }
        pass = null;
        return false;
    }
    
    public void DeleteSchedule(String name, String date, String flightID){
        ConnectionToDB();
        String sql = "DELETE from BOOKING where NAME = ? and DATE = ? and FLIGHT = ?";
        try
        {
            pass = connection.prepareStatement(sql);
            
            pass.setString(1, name);
            pass.setString(2, date);
            pass.setString(3, flightID);

            pass.executeUpdate();
            pass = null;

        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    
    public void DeleteFromWaitList(String name, String date, String flightID)
    {
        ConnectionToDB();
        String sql = "DELETE from WAITLIST where NAME =? and DATE =? and FLIGHT =?";
        try{
            pass = connection.prepareStatement(sql);
            
            pass.setString(1, name);
            pass.setString(2, date);
            pass.setString(3, flightID);
                    
            pass.executeUpdate();
            pass = null;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    


    public boolean maybeInWaitList(String name,String date,String flightID)
    {
        ConnectionToDB();
        try 
        {
            waitlistFeedback = connection.prepareStatement("SELECT * from WAITLIST");
            ResultSet rs = waitlistFeedback.executeQuery();
            
            while(rs.next())
            {
                String selectedFlight = rs.getString("FLIGHT");
                String selectedDate = rs.getString("DATE");
                String selectedName = rs.getString("NAME");
                
                if(selectedName.equals(name)&&selectedFlight.equals(flightID)&&selectedDate.equals(date))
                {
                    return true;
                
                }
            }
        }
        catch (Exception except)
        {
            except.printStackTrace();
        } 
        waitlistFeedback = null;
        return false;
    }
}
