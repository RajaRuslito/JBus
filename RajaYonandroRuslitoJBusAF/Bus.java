package RajaYonandroRuslitoJBusAF;
import java.util.List;
import java.util.Calendar;
import java.util.ArrayList;

public class Bus extends Serializable{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    public Station departure;
    public Station arrival;
    public BusType busType;
    public City city;
    public List<Schedule> schedules;
    
    public Bus(int id, String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival){
        super(id);
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.capacity = capacity;
        this.departure = departure;
        this.arrival = arrival;
        this.busType = busType;
        this.city = city;
        this.schedules = new ArrayList<>();
    }
    public void addSchedule(Calendar calendar){
        Schedule schedule = new Schedule(calendar, capacity);
        schedules.add(schedule);
    }
    public void printSchedule(Schedule schedule){
        System.out.println("Tanggal Keberangkatan : " + schedule.departureSchedule.getTime());
        System.out.println("Daftar Kursi dan Ketersediaan Kursi : ");
        System.out.println(schedule.seatAvailability);
    }
    public boolean read(){
        return false;        
    }
    public Object write(){
        return null;
    }
    public String toString(){
        return " ID : " + id + "\n Name : " + name + "\n Facility : " + facility + "\n Price : " + price + "\n Capacity : " + capacity + "\n Bus Type : " + busType + "\n City : " + city + "\n\n Departure = " + departure + "\n Arrival = " + arrival;
    }
}
