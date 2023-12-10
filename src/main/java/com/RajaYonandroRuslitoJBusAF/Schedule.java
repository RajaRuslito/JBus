package com.RajaYonandroRuslitoJBusAF;

import java.util.List;
import java.util.Map;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.text.*;

/**
 * Represents the schedule for a bus, including departure time and seat availability.
 */
public class Schedule {
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;

    /**
     * Constructs a Schedule object with the given departure schedule and number of seats.
     *
     * @param departureSchedule The timestamp indicating the departure schedule.
     * @param numberOfSeats     The total number of seats in the bus.
     */
    public Schedule(Timestamp departureSchedule, int numberOfSeats) {
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }

    // Private method to initialize seat availability
    private void initializeSeatAvailability(int numberOfSeats) {
        seatAvailability = new LinkedHashMap<>();
        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++) {
            String sn = seatNumber < 10 ? "0" + seatNumber : "" + seatNumber;
            seatAvailability.put("AF" + sn, true);
        }
    }

    /**
     * Checks if a single seat is available.
     *
     * @param seat The seat to check for availability.
     * @return True if the seat is available, false otherwise.
     */
    public boolean isSeatAvailable(String seat) {
        if (seatAvailability.containsKey(seat)) {
            return seatAvailability.get(seat);
        }
        return false;
    }

    /**
     * Checks if a list of seats are available.
     *
     * @param seat The list of seats to check for availability.
     * @return True if all seats are available, false otherwise.
     */
    public boolean isSeatAvailable(List<String> seat) {
        for (int i = 0; i < seat.size(); i++) {
            if (seatAvailability.containsKey(seat.get(i))) {
                return seatAvailability.get(seat.get(i));
            }
        }
        return false;
    }

    /**
     * Books a single seat, marking it as unavailable.
     *
     * @param seat The seat to be booked.
     */
    public void bookSeat(String seat) {
        this.seatAvailability.put(seat, false);
    }

    /**
     * Books a list of seats, marking them as unavailable.
     *
     * @param seat The list of seats to be booked.
     */
    public void bookSeat(List<String> seat) {
        for (String i : seat) {
            seatAvailability.put(i, false);
        }
    }

    /**
     * Prints the schedule with seat availability details.
     */
    public void printSchedule() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());
        // Print tanggal keberangkatan
        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);
        System.out.println("Daftar kursi dan ketersediaan kursinya: ");

        int maxSeatsPerRow = 4;
        int currentSeat = 1;
        for (String seat : this.seatAvailability.keySet()) {
            String symbol = this.seatAvailability.get(seat) ? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");
            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat++;
        }
        System.out.println("\n");
    }

    /**
     * Returns a string representation of the Schedule object.
     *
     * @return A string containing the departure schedule and seat occupancy details.
     */
    @Override
    public String toString() {
        int occupy = Algorithm.count(seatAvailability.values().iterator(), true);
        return "Schedule\t: " + departureSchedule + "\nOccupied\t: " + occupy + "\n" + seatAvailability.size();
    }
}
