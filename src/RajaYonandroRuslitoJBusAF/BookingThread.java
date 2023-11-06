package RajaYonandroRuslitoJBusAF;

import java.sql.Timestamp;
public class BookingThread extends Thread {
    private Bus bus;
    private Timestamp timestamp;

    public BookingThread(String name, Bus bus, Timestamp timestamp) {
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start();
    }
    @Override
    public void run() {
        System.out.println(super.getName() + " ID : " + super.getId() + " is running");
        //System.out.println("Id Thread " + super.getId());
        for(Schedule schedule : bus.schedules) {
            if(schedule.departureSchedule.equals(timestamp)) {
                String res = Payment.makeBooking(timestamp, "AF01", bus) ? "Berhasil" : "Gagal";
                System.out.println("Thread " + super.getId() + " " + res + " Melakukan Booking");
            }
        }
    }

    /*public void run() {
        for(Schedule schedule : bus.schedules) {
            if(schedule.departureSchedule.equals(timestamp)) {
                System.out.println(this.getName() + " ID : " + this.getId());
                String res = Payment.makeBooking(timestamp, "AF01", bus) ? "Berhasil" : "Gagal";
                System.out.println(this.getName() + " ID : " + this.getId() + " " + res);
            }
        }
    }*/
}