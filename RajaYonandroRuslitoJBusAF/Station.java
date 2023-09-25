package RajaYonandroRuslitoJBusAF;

public class Station extends Serializable
{
    public City city;
    public String stationName;
    
    public Station(int id, String stationName, City city){
        super(id);
        this.stationName = stationName;
        this.city = city;
    }
    public String print(){
        String printThis = "ID : " + id + "\n Station Name : " + stationName + "\n City : " + city.toString();
        return printThis;
    }
}
