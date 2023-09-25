package RajaYonandroRuslitoJBusAF;

public class Bus extends Serializable{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    public Station departure;
    public Station arrival;
    public BusType busType;
    public City city;
    
    
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
    }
    public String toString(){
        return " ID : " + id + "\n Name : " + name + "\n Facility : " + facility + "\n Price : " + price + "\n Capacity : " + capacity + "\n Bus Type : " + busType + "\n City : " + city + "\n\n Departure = " + departure + "\n Arrival = " + arrival;
    }
}
