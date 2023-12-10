package com.RajaYonandroRuslitoJBusAF;

import com.RajaYonandroRuslitoJBusAF.dbjson.Serializable;

import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;

/**
 * Represents a bus entity with associated details such as name, facilities, price, capacity, bus type, departure,
 * arrival, schedules, and account ID.
 * Extends Serializable for serialization purposes.
 */
public class Bus extends Serializable {
    public int capacity;
    public List<Facility> facilities;
    public String name;
    public Price price;
    public Station departure;
    public Station arrival;
    public BusType busType;
    public List<Schedule> schedules;
    public int accountId;

    /**
     * Constructor to initialize a Bus object with the provided details.
     *
     * @param name       Name of the bus.
     * @param facilities List of facilities available in the bus.
     * @param price      Price of the bus.
     * @param capacity   Capacity of the bus.
     * @param busType    Type of the bus.
     * @param departure  Departure station of the bus.
     * @param arrival    Arrival station of the bus.
     * @param accountId  Account ID associated with the bus.
     */
    public Bus(String name, List<Facility> facilities, Price price, int capacity, BusType busType/*, City city*/, Station departure, Station arrival, int accountId) {
        super();
        this.name = name;
        this.facilities = facilities;
        this.price = price;
        this.capacity = capacity;
        this.departure = departure;
        this.arrival = arrival;
        this.busType = busType;
        //this.city = city;
        this.schedules = new ArrayList<>();
        this.accountId = accountId;
    }

    public String toString() {
        return " ID : " + id + "\n String objek : " + name + "\n Facility : " + facilities + "\n Price : " + price + "\n Capacity : " + capacity + "\n Bus Type : " + busType + "\n City : " /*+ city*/ + "\n\n Departure = " + departure + "\n Arrival = " + arrival;
    }

    public boolean read(String obj) {
        return false;
    }

    public Object write() {
        return null;
    }

    /**
     * Adds a schedule to the list of schedules associated with the bus.
     *
     * @param timestamp Timestamp of the schedule.
     */
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
