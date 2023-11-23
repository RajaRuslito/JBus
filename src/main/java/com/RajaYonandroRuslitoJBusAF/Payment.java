package com.RajaYonandroRuslitoJBusAF;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Payment extends Invoice
{
    private int busId;
    public Timestamp departureDate;
    public List<String> busSeats;

    public Payment(int buyerId, int renterId, int busId, List<String> busSeats, Timestamp departureDate) {
        super(buyerId, renterId);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeats = busSeats;
    }

    public Payment(Account buyer, Renter renter, int busId, List<String> busSeats, Timestamp departureDate) {
        super(buyer, renter);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeats = busSeats;
    }

    /*
    @Override
    public String toString(){
        String printLine = "Payment Details ="  + " | ID : " + String.valueOf(id) + " | Bus ID : " + String.valueOf(busId) + " | Departure Date : " + departureDate.getTime() + " | Bus Seat : " + busSeat + " |";
        return printLine;
    }
    */

    public int getBusId() {
        return this.busId;
    }

    public String getDepartureInfo() {
        SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String date = format.format(this.departureDate.getTime());
        String printLine = "Payment Details ="  + " | ID : " + String.valueOf(id) + " | Bus ID : " + String.valueOf(busId) + " | Departure Date : " + date + " | Bus Seat : " + this.busSeats.toString() + " |";
        return printLine;
    }

    public String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");

        return format.format(this.departureDate.getTime());
    }

    public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus) {

        for(Schedule sched : bus.schedules) {
            if(sched.departureSchedule.equals(departureSchedule)) {
                for(String seatName : sched.seatAvailability.keySet()) {
                    if(seatName.equals(seat)) {
                        return sched.seatAvailability.get(seat);
                    }
                }
            }
        }

        return false;
    }

    public static Schedule availableSchedule(Timestamp timestamp, String seat, Bus bus) {
        for(Schedule sched : bus.schedules) {
            if(sched.departureSchedule.getTime() == timestamp.getTime()) {
                for(String s : sched.seatAvailability.keySet()) {
                    if(s.equals(seat)) {
                        return sched;
                    }
                }
            }
        }

        return null;
    }

    public static Schedule availableSchedule(Timestamp timestamp, List<String> list, Bus bus) {
        for(Schedule sched : bus.schedules) {
            if(sched.departureSchedule.getTime() == timestamp.getTime()) {
                if(sched.seatAvailability.keySet().containsAll(list)) {
                    return sched;
                }
            }
        }

        return null;
    }

    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus) {
        for(Schedule sched : bus.schedules) {
            if(sched.departureSchedule.equals(departureSchedule)) {
                for(String seatName : sched.seatAvailability.keySet()) {
                    if(seatName.equals(seat) && sched.seatAvailability.get(seat)) {
                        sched.bookSeat(seat);
                        return true;
                    }
                }
            }
        }


        return false;
    }
}

