package RajaYonandroRuslitoJBusAF;

public class Station extends Serializable
{
    public String address;
    public City city;
    public String stationName;
        
    public Station(int id, String stationName, City city, String address){
        super();
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }
    public String toString(){
        return "\n  ID : " + id + "\n  Station Name : " + stationName + "\n  City : " + city + "\n  Address : " + address;
    }
}
