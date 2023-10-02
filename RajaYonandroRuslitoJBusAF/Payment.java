package RajaYonandroRuslitoJBusAF;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

public class Payment extends Invoice
{
    private int busId;
    public Timestamp departureDate;
    public String busSeat;
    
    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat, Timestamp departureDate){
        super(id, buyer, renter);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
        //departureDate.add(Calendar.DATE, 2);
    }
    public Payment(int id, int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate){
        super(id, buyerId, renterId);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
        //departureDate.add(Calendar.DATE, 2);
    }
    public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus){
        for(Schedule baru : bus.schedules){
            if(baru.departureSchedule.equals(departureSchedule) && baru.isSeatAvailable(seat)){
                return true;
            }
        }
        return false;
    }
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus){
        for(Schedule baru : bus.schedules){
            if(baru.departureSchedule.equals(departureSchedule) && baru.isSeatAvailable(seat)){
                baru.bookSeat(seat);
                return true;
            }
        }
        return false;
    }
    public String getDepartureInfo(){
        SimpleDateFormat formatted = new SimpleDateFormat("MMMM, d yyyy hh:mm:ss"); 
        String formattedDate = formatted.format(departureDate.getTime());
        return "\nStation Details = " + "\n ID : " + id + "\n Buyer ID : " + buyerId + "\n Renter ID : " + renterId + "\n Bus ID : " + busId + "\n Departure Date : " + formattedDate + "\n Bus Seat : " + busSeat;
    }
    public String getTime(){
        SimpleDateFormat formatted = new SimpleDateFormat("MMMM, d yyyy hh:mm:ss"); 
        String formattedDate = formatted.format(super.time.getTime());
        return formattedDate;
    }
    public int getBusid(){
        return busId;
    }
}
