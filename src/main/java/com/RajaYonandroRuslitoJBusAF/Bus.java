package com.RajaYonandroRuslitoJBusAF;
import com.RajaYonandroRuslitoJBusAF.dbjson.Serializable;

import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;

public class Bus extends Serializable
{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    public Station departure;
    public Station arrival;
    public BusType busType;
    public City city;
    public List<Schedule> schedules;
    
    public Bus(String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival){
        super();
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
    /*public void addSchedule(Timestamp calendar){
        Schedule schedule = new Schedule(calendar, capacity);
        schedules.add(schedule);
    }*/
    public String toString(){
        return " ID : " + id + "\n String objek : " + name + "\n Facility : " + facility + "\n Price : " + price + "\n Capacity : " + capacity + "\n Bus Type : " + busType + "\n City : " + city + "\n\n Departure = " + departure + "\n Arrival = " + arrival;
    }
    public boolean read(String obj){
        return false;        
    }
    public Object write(){
        return null;
    }

    public void addSchedule(Timestamp timestamp) {
        boolean isExisting = false;
        Schedule schedule = new Schedule(timestamp, capacity);
        for (Schedule sched : schedules) {
            if (sched.departureSchedule.equals(schedule.departureSchedule)) {
                isExisting = true;
            }
        }

        if (!isExisting) {
            schedules.add(schedule);
        }
    }
    
}
