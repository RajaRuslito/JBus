package com.RajaYonandroRuslitoJBusAF;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.text.*;


public class Schedule
{
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;
    
    public Schedule(Timestamp departureSchedule, int numberOfSeats){
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }
    
    private void initializeSeatAvailability(int numberOfSeats){
        seatAvailability = new LinkedHashMap<>();
          for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++) {
            String sn = seatNumber < 10 ? "0" + seatNumber : ""+ seatNumber;
            seatAvailability.put("AF" + sn, true); 
        }
    }
    public boolean isSeatAvailable(String seat){
         if(seatAvailability.containsKey(seat)){
            return seatAvailability.get(seat);
        }
        return false;
    }
    public boolean isSeatAvailable(List<String> seat){
        for(int i = 0; i < seat.size(); i++){
            if(seatAvailability.containsKey(seat.get(i))){
                return seatAvailability.get(seat.get(i));
            }
        }
        return false;
    }
    public void bookSeat(String seat){
        this.seatAvailability.put(seat, false);
    }
    public void bookSeat(List<String> seat){
        for(String i : seat){
            seatAvailability.put(i, false);
        }
    }
    public void printSchedule(){
        /*SimpleDateFormat format = new SimpleDateFormat ("\n'Tanggal keberangkatan: 'MMMM dd, yyyy HH:mm:ss");
        
        System.out.println(format.format(schedule.departureSchedule.getTime()));
        System.out.println("Daftar Kursi dan Ketersediaan Kursi : ");     
        
        int maxSeatsPerRow = 4;
        int currentSeat = 1;
        
        for(String seat : schedule.seatAvailability.keySet()){
            System.out.print(seat + " : " + schedule.seatAvailability.get(seat) + "\t");
            
            if(currentSeat % maxSeatsPerRow == 0){
                System.out.println();
            }
            currentSeat++;
        }*/
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());
        // Print tanggal keberangkatan
        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);
        System.out.println("Daftar kursi dan ketersediaan kursinya: ");
        
        int maxSeatsPerRow = 4; 
        int currentSeat = 1;
        for (String seat : this.seatAvailability.keySet()){
            String symbol = this.seatAvailability.get(seat)? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");
            if (currentSeat % maxSeatsPerRow == 0){
                System.out.println();
            }
            currentSeat++;
        }
        System.out.println("\n");
    }

    @Override
    public String toString(){
        int occupy = Algorithm.count(seatAvailability.values().iterator(), true);
        return "Schedule\t: " + departureSchedule + "\nOccupied\t: " +  occupy + "\n" + seatAvailability.size();
    }
}
