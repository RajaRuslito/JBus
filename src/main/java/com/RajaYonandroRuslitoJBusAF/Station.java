package com.RajaYonandroRuslitoJBusAF;

import com.RajaYonandroRuslitoJBusAF.dbjson.Serializable;

/**
 * Represents a bus station with a unique ID, station name, city, and address.
 */
public class Station extends Serializable
{
    public String address;
    public City city;
    public String stationName;

    /**
     * Constructs a Station object with the given station name, city, and address.
     *
     * @param stationName The name of the bus station.
     * @param city        The city where the bus station is located.
     * @param address     The address of the bus station.
     */
    public Station(String stationName, City city, String address){
        super();
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }

    /**
     * Returns a string representation of the Station object.
     *
     * @return A string containing the ID, station name, city, and address of the bus station.
     */
    public String toString(){
        return "\n  ID : " + id + "\n  Station Name : " + stationName + "\n  City : " + city + "\n  Address : " + address + "\n";
    }
}
