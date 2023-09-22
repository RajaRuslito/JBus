package RajaYonandroRuslitoJBusAF;

public class Station extends Serializable
{
    City city;
    String stationName;
    
    public Station(int id, String stationName, City city){
        super(id);
        this.stationName = stationName;
        this.city = city;
    }
    public String print(){
        String printThis = " Station Name : " + stationName + "\n City : " + city.toString();
        return printThis;
    }
}
