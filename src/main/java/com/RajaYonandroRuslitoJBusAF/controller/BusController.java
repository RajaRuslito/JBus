package com.RajaYonandroRuslitoJBusAF.controller;

import com.RajaYonandroRuslitoJBusAF.*;
import com.RajaYonandroRuslitoJBusAF.dbjson.JsonAutowired;
import com.RajaYonandroRuslitoJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus>  {

    public static @JsonAutowired(value = Bus.class, filepath = "src\\main\\java\\com\\RajaYonandroRuslitoJBusAF\\json\\bus.json") JsonTable<Bus> busTable;
    //public static JsonTable<Bus> busTable;
    @Override
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }

    @PostMapping("/create")
    public BaseResponse<Bus> create(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam List<Facility> facilities,
            @RequestParam BusType busType,
            @RequestParam int price,
            @RequestParam int stationDepartureId,
            @RequestParam int stationArrivalId
    ){
/*
        try {*/

            Account newacc = Algorithm.<Account>find(AccountController.accountTable, e -> e.id == accountId && e.company != null);
            Station departure = Algorithm.<Station>find(StationController.stationTable, e -> e.id == stationDepartureId);
            Station arrival = Algorithm.<Station>find(StationController.stationTable, e -> e.id == stationArrivalId);
            // Validate parameters
            if (newacc == null) {
                return new BaseResponse<>(false, "Account Tidak Ditemukan", null);
            }
            if(departure == null || arrival == null){
                return new BaseResponse<>(false, "Jadwal Departure/Arrival Tidak Ditemukan", null);
            }
            Bus newbus = new Bus(name, facilities, new Price(price), capacity, busType, departure,  arrival, accountId);
            busTable.add(newbus);
            return new BaseResponse<>(true, "Jadwal Bus Telah Dibuat", newbus);
    }

    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(
            @RequestParam int busId,
            @RequestParam String time
    ){
        Bus newbus = Algorithm.<Bus>find(busTable, e -> e.id == busId);
        Timestamp timestamp = Timestamp.valueOf(time);
        if(newbus != null){
            try{
                newbus.addSchedule(timestamp);
            }
            catch(Exception e){
                e.printStackTrace();
                return new BaseResponse<>(false, "Schedule Gagal Ditambah", null);
            }
            return new BaseResponse<>(true, "Schedule Telah Ditambah", newbus);
        }
        return new BaseResponse<>(false, "Bus Tidak Ditemukan", null);
    }
}
