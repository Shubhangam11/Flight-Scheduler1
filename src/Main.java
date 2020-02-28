
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;
import java.util.*;
import java.util.Date;
import javax.swing.JOptionPane;


public class Main extends javax.swing.JFrame implements ActionListener {

    private DatabaseConnection database;
    private Booking booking;
    
    public Main() {
        initComponents();
        booking = new Booking();
        database = new DatabaseConnection();
        newFlightComboBox();
        newDatesComboBox();
        getFlight();
    }

    private void getFlight(){
        ArrayList<String> original_FlightNames = database.getFlightList();
        for(int i = 0; i < original_FlightNames.size(); i++){
            Flight newFlight = new Flight(original_FlightNames.get(i), 2);
           
        }
    }
    
    private void newFlightComboBox() {
        flight_for_booking_ComboBox.removeAllItems();
        FlightComboBox1.removeAllItems();
        ArrayList<String> flightList = database.getFlightList();
        for (int i = 0; i < flightList.size(); i++){
            String new_data = flightList.get(i);
            //System.out.println(i +": [FLIGHTS]: adding " + temp);
            flight_for_booking_ComboBox.addItem(new_data);
            FlightComboBox1.addItem(new_data);
        }
    }
    
    private void newDatesComboBox() 
    {
        date_for_bookingComboBox.removeAllItems();
        AvailDateComboBox.removeAllItems();
        int i = 0;
        ArrayList dateList = database.getDatesList();
        while (i < dateList.size()){
            String temp = dateList.get(i).toString();
            date_for_bookingComboBox.addItem(temp);
            AvailDateComboBox.addItem(temp);
            i++;
        }
    }
    
    private void Month()
    {
        String x;
        for (int i = 0; i < 31; i++){
            x = String.valueOf(i+1);
            DateComboBox.addItem(x);
        }
    }
    
    private int getMaxSeats(String flightID){
        int x = database.getMaxSeats(flightID);
        //System.out.println(x);
        return x;
    }
    
    private int getSeatsTaken(String flightID, String date){
        int x = booking.getSeatNumber(flightID);
        return x;
    }
    
    private String getWaitlists(String flightID, String date){
        String x = booking.getWaitList(flightID, date);
        return x;
    }
    
    private String getPassengers(String flightID, String date){
        String x = booking.Passenger_Info(flightID, date);
        return x;
    }
    

    
    private void initComponents() {

        statusLabel = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        customerTab = new javax.swing.JPanel();
        customerLabel = new javax.swing.JLabel();
        customerField = new javax.swing.JTextField();
        BookingFlightButton = new javax.swing.JButton();
        flight_for_booking_ComboBox = new javax.swing.JComboBox<>();
        dateLabel = new javax.swing.JLabel();
        date_for_bookingComboBox = new javax.swing.JComboBox<>();
        flightLabel = new javax.swing.JLabel();
        CheckStatusButton = new javax.swing.JButton();
        CheckWaitListButton = new javax.swing.JButton();
        CancelFlightButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        customerNamePrompt = new javax.swing.JLabel();
        customerField1 = new javax.swing.JTextField();
        customerStatusCheckButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        availableDatesLabel = new javax.swing.JLabel();
        AvailDateComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        MonthComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        DateComboBox = new javax.swing.JComboBox<>();
        yearLabel = new javax.swing.JLabel();
        YearComboBox = new javax.swing.JComboBox<>();
        AddingDateButton = new javax.swing.JButton();
        flightTab = new javax.swing.JPanel();
        flightIDLabel = new javax.swing.JLabel();
        flightIDataField = new javax.swing.JTextField();
        newFlightButton = new javax.swing.JButton();
        FlightComboBox1 = new javax.swing.JComboBox<>();
        droppingFlightButton = new javax.swing.JButton();
        availableFlightsLabel = new javax.swing.JLabel();
        maxSeatsLabel = new javax.swing.JLabel();
        MaximumSeatsFlightField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Status_output_Bar = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Flight Boking System");

        statusLabel.setText("Status:");

        customerLabel.setText("Customer Name:");
        customerLabel.setToolTipText("");
        customerLabel.setName(""); // NOI18N

        customerField.setToolTipText("Enter the name of the passenger here");

        BookingFlightButton.setText("Book Flight");
        BookingFlightButton.setToolTipText("Book selected flight for the selected day");
        BookingFlightButton.setName(""); // NOI18N
        BookingFlightButton.addActionListener(this);

        flight_for_booking_ComboBox.setToolTipText("All flights");

        dateLabel.setText("Date:");

        date_for_bookingComboBox.setToolTipText("Available dates for flights");

        flightLabel.setText("Flight:");

        CheckStatusButton.setText("Check Status");
        CheckStatusButton.setToolTipText("Checks for flight availability");
        CheckStatusButton.addActionListener(this);

        CheckWaitListButton.setText("Check Waitlist");
        CheckWaitListButton.setToolTipText("Checks the waitlist for the selected flight and day");
        CheckWaitListButton.addActionListener(this);

        CancelFlightButton.setText("Cancel Flight");
        CancelFlightButton.addActionListener(this);

        javax.swing.GroupLayout customerTabLayout = new javax.swing.GroupLayout(customerTab);
        customerTab.setLayout(customerTabLayout);
        customerTabLayout.setHorizontalGroup(
            customerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerTabLayout.createSequentialGroup()
                        .addComponent(customerLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customerField))
                    .addGroup(customerTabLayout.createSequentialGroup()
                        .addGroup(customerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLabel)
                            .addComponent(flightLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(customerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(flight_for_booking_ComboBox, 0, 209, Short.MAX_VALUE)
                            .addComponent(date_for_bookingComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                        .addGroup(customerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CheckWaitListButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CheckStatusButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(customerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CancelFlightButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BookingFlightButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8)))
                .addGap(78, 78, 78))
        );
        customerTabLayout.setVerticalGroup(
            customerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerLabel)
                    .addComponent(customerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date_for_bookingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateLabel)
                    .addComponent(CheckStatusButton)
                    .addComponent(BookingFlightButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(flightLabel)
                    .addComponent(flight_for_booking_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CheckWaitListButton)
                    .addComponent(CancelFlightButton))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Book Flight", customerTab);

        customerNamePrompt.setText("Customer Name:");

        customerStatusCheckButton.setText("Check Status");
        customerStatusCheckButton.setToolTipText("Checks status of scheduled flights and waitlist reservations for the customer");
        customerStatusCheckButton.addActionListener(this);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(customerNamePrompt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customerField1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(542, Short.MAX_VALUE)
                .addComponent(customerStatusCheckButton)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerNamePrompt)
                    .addComponent(customerField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(customerStatusCheckButton)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Customer", jPanel2);

        availableDatesLabel.setText("Available Dates:");

        jLabel1.setText("What date you prefer?");

        jLabel2.setText("Month:");

        MonthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        MonthComboBox.addActionListener(this);

        jLabel3.setText("Day:");

        yearLabel.setText("Year:");

        YearComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018" }));

        AddingDateButton.setText("Add Date");
        AddingDateButton.addActionListener(this);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(availableDatesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AvailDateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(yearLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(YearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddingDateButton)))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(availableDatesLabel)
                    .addComponent(AvailDateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(MonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(DateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearLabel)
                    .addComponent(YearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddingDateButton))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dates", jPanel3);

        flightIDLabel.setText("Digit Flight Number:");

        newFlightButton.setText("Create Flight");
        newFlightButton.addActionListener(this);

        droppingFlightButton.setText("Drop Flight");
        droppingFlightButton.addActionListener(this);

        availableFlightsLabel.setText("Available Flights:");

        maxSeatsLabel.setText("Maximum Seats:");

        javax.swing.GroupLayout flightTabLayout = new javax.swing.GroupLayout(flightTab);
        flightTab.setLayout(flightTabLayout);
        flightTabLayout.setHorizontalGroup(
            flightTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(flightTabLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(flightTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(availableFlightsLabel)
                    .addComponent(flightIDLabel)
                    .addComponent(maxSeatsLabel))
                .addGroup(flightTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(flightTabLayout.createSequentialGroup()
                        .addGroup(flightTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(flightTabLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(flightIDataField, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, flightTabLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(MaximumSeatsFlightField, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(newFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, flightTabLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(FlightComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(droppingFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        flightTabLayout.setVerticalGroup(
            flightTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(flightTabLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(flightTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(droppingFlightButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(flightTabLayout.createSequentialGroup()
                        .addGroup(flightTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(flightIDLabel)
                            .addComponent(flightIDataField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(flightTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MaximumSeatsFlightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maxSeatsLabel)))
                    .addComponent(newFlightButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(flightTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(availableFlightsLabel)
                    .addComponent(FlightComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        jTabbedPane1.addTab("Flights", flightTab);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
        );

        Status_output_Bar.setColumns(20);
        Status_output_Bar.setRows(5);
        Status_output_Bar.setToolTipText("");
        jScrollPane1.setViewportView(Status_output_Bar);

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 153));
        jLabel5.setText("FLIGHT BOOKING SYSTEM");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(statusLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(jLabel5)
                        .addGap(67, 67, 67)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(statusLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }

    // Code for dispatching events from components to event handlers.

    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == BookingFlightButton) {
            Main.this.BookingFlightButtonActionPerformed(evt);
        }
        else if (evt.getSource() == CheckStatusButton) {
            Main.this.CheckStatusButtonActionPerformed(evt);
        }
        else if (evt.getSource() == CheckWaitListButton) {
            Main.this.CheckWaitListButtonActionPerformed(evt);
        }
        else if (evt.getSource() == CancelFlightButton) {
            Main.this.CancelFlightButtonActionPerformed(evt);
        }
        else if (evt.getSource() == customerStatusCheckButton) {
            Main.this.customerStatusCheckButtonActionPerformed(evt);
        }
        else if (evt.getSource() == MonthComboBox) {
            Main.this.MonthComboBoxActionPerformed(evt);
        }
        else if (evt.getSource() == AddingDateButton) {
            Main.this.AddingDateButtonActionPerformed(evt);
        }
        else if (evt.getSource() == newFlightButton) {
            Main.this.newFlightButtonActionPerformed(evt);
        }
        else if (evt.getSource() == droppingFlightButton) {
            Main.this.droppingFlightButtonActionPerformed(evt);
        }
    }

    private void BookingFlightButtonActionPerformed(java.awt.event.ActionEvent evt) {
       
        String date = date_for_bookingComboBox.getSelectedItem().toString();
        String flightID = flight_for_booking_ComboBox.getSelectedItem().toString();
        String name = customerField.getText();

        if(booking.MakeReservation(date,flightID, name))
        {
        Status_output_Bar.setText("Flight Booked! Flight Number " + flightID + " on " + date + " added to records.");
        }
        else {
            Status_output_Bar.setText("Flight could not be booked, all seats are taken. Information recorded to WAITLIST.");
        }
    }

    private void CheckStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String date = date_for_bookingComboBox.getSelectedItem().toString();
        String flightID = flight_for_booking_ComboBox.getSelectedItem().toString();
        String passengers = getPassengers(flightID, date);

        int openSeats = getMaxSeats(flightID) - (getSeatsTaken(flightID, date) - 1);
        if(booking.openSeats(date, flightID)){
            Status_output_Bar.setText("The flight " + flightID + " on " + date + " has available seats."
                + " There are " + openSeats + " open Seats.\nPassengers scheduled:\n" + passengers);
        }
        else {
            Status_output_Bar.setText("The Flight is full. \nPassengers:\n" + passengers+"\nCheck WaitList Status!!\n");
        }
    }

    private void CheckWaitListButtonActionPerformed(java.awt.event.ActionEvent evt) {
       
        String date = date_for_bookingComboBox.getSelectedItem().toString();
        String flightID = flight_for_booking_ComboBox.getSelectedItem().toString();
        String waitlist_comments = getWaitlists(flightID, date);
        if(waitlist_comments.equals("")){
            Status_output_Bar.setText("There is no waitlist for the flight, seats might still be open.");
        }
        else {
            Status_output_Bar.setText( "Waitlist for flight: "+ flightID + " on " + date + ":\n" + waitlist_comments);
        }
    }

    private void CancelFlightButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String name = customerField.getText();
        String date = date_for_bookingComboBox.getSelectedItem().toString();
        String flightID = flight_for_booking_ComboBox.getSelectedItem().toString();
        booking.DeleteSchedule(name, date, flightID);
        if(booking.maybeInWaitList(name,date,flightID))
        {
            booking.DeleteFromWaitList(name, date, flightID);
        };
        Status_output_Bar.setText("Record deleted.  \nPlease Check Status for your flight schedule\n");
    }

    private void customerStatusCheckButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String name = customerField1.getText();
        String status = booking.GetPassengerStatus(name);
        Status_output_Bar.setText(status);
    }

    private void MonthComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
            DateComboBox.removeAllItems();
            int month = MonthComboBox.getSelectedIndex() + 1;
            String x = "";
            System.out.println(month);

            if (month == 2)
            {
                for (int i = 0; i < 28; i++){
                    x = String.valueOf(i+1);
                    DateComboBox.addItem(x);
                    //System.out.println(x);
                }
            }
            else if (month == 1|| month == 3 || month == 5 || month == 7 ||
                month == 8 || month == 10 || month == 12){
                for (int i = 0; i < 31; i++){
                    x = String.valueOf(i+1);
                    DateComboBox.addItem(x);
                }
            }
            else {
                for (int i = 0; i < 30; i++){
                    x = String.valueOf(i+1);
                    DateComboBox.addItem(x);
                }
            }
    }//GEN-LAST:event_MonthComboBoxActionPerformed

    private void AddingDateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String year = YearComboBox.getSelectedItem().toString();
        int rawmonth = MonthComboBox.getSelectedIndex() + 1;
        String month = String.valueOf(rawmonth);
        String day = DateComboBox.getSelectedItem().toString();

        if (month.length() < 2){
            month = "0" + String.valueOf(rawmonth);
        }
        if (day.length() < 2){
            day = "0" + day;
        }

        String date = year + "-" + month + "-" + day;
        //        System.out.println(date);
        booking.AddNewDate(date);
        newDatesComboBox();
        Status_output_Bar.setText("Date added to system: [" + date + "]");
    }

    private void newFlightButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String flightID = flightIDataField.getText();
        int maxSeats = 0;
        maxSeats = Integer.parseInt(MaximumSeatsFlightField.getText().toString());
        if ((flightID != null) && (maxSeats > 0)){
            database.addFlight(flightID, maxSeats);
            newFlightComboBox();
            Status_output_Bar.setText("Flight added. ID [" + flightID + "] with max seats: " + maxSeats);
        }
        else {
            Status_output_Bar.setText("Invalid Flight. A name and number of seats must be provided.");
        }
    }

    private void droppingFlightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_droppingFlightButtonActionPerformed
        // remove a flight from the list
        String flightID = FlightComboBox1.getSelectedItem().toString();
        if(booking.ScheduledPassengers(flightID)){
            booking.Rebooking(flightID);
            database.deleteFlight(flightID);
        }
        else {
            database.deleteFlight(flightID);
        }
        newFlightComboBox();
        Status_output_Bar.setText("Flight dropped.");
    }

    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    
    
   
    public javax.swing.JButton AddingDateButton;
    public javax.swing.JComboBox<String> AvailDateComboBox;
    public javax.swing.JButton BookingFlightButton;
    public javax.swing.JButton CancelFlightButton;
    public javax.swing.JButton CheckStatusButton;
    public javax.swing.JButton CheckWaitListButton;
    public javax.swing.JComboBox<String> DateComboBox;
    public javax.swing.JComboBox<String> FlightComboBox1;
    public javax.swing.JTextField MaximumSeatsFlightField;
    public javax.swing.JComboBox<String> MonthComboBox;
    public javax.swing.JTextArea Status_output_Bar;
    public javax.swing.JComboBox<String> YearComboBox;
    public javax.swing.JLabel availableDatesLabel;
    public javax.swing.JLabel availableFlightsLabel;
    public javax.swing.JTextField customerField;
    public javax.swing.JTextField customerField1;
    public javax.swing.JLabel customerLabel;
    public javax.swing.JLabel customerNamePrompt;
    public javax.swing.JButton customerStatusCheckButton;
    public javax.swing.JPanel customerTab;
    public javax.swing.JLabel dateLabel;
    public javax.swing.JComboBox<String> date_for_bookingComboBox;
    public javax.swing.JButton droppingFlightButton;
    public javax.swing.JLabel flightIDLabel;
    public javax.swing.JTextField flightIDataField;
    public javax.swing.JLabel flightLabel;
    public javax.swing.JPanel flightTab;
    public javax.swing.JComboBox<String> flight_for_booking_ComboBox;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JLabel maxSeatsLabel;
    public javax.swing.JButton newFlightButton;
    public javax.swing.JLabel statusLabel;
    public javax.swing.JLabel yearLabel;
    // End of variables declaration//GEN-END:variables
}
