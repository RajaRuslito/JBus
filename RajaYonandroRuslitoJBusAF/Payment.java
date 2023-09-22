package RajaYonandroRuslitoJBusAF;

public class Payment extends Invoice
{
    private int busId;
    public String departureDate;
    public String busSeat;
    
    public Payment(int id, Account buyer, Renter renter, String time, int busId, String departureDate, String busSeat){
        super(id, buyer, renter, time);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    public Payment(int id, int buyerId, int renterId, String time, int busId, String departureDate, String busSeat){
        super(id, buyerId, renterId, time);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    public String print(){
        String printThis = "Station Details = " + "\n Bus ID : " + busId + "\n Departure : " + departureDate + "\n Bus Seat : " + busSeat;
        return printThis;
    }
    public int getBusid(){
        return busId;
    }
}
