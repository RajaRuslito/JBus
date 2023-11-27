package com.RajaYonandroRuslitoJBusAF.controller;

import com.RajaYonandroRuslitoJBusAF.*;
import com.RajaYonandroRuslitoJBusAF.dbjson.JsonAutowired;
import com.RajaYonandroRuslitoJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

            /*Account newacc = Algorithm.<Account>find(AccountController.accountTable, e -> e.id == accountId && e.company != null);
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
            return new BaseResponse<>(true, "Jadwal Bus Telah Dibuat", newbus);*/
        JsonTable<Station> stations;
        JsonTable<Account> accounts;
        try {
            accounts = new JsonTable<>(Account.class, AccountController.accountTable.filepath);
            stations = new JsonTable<>(Station.class, StationController.stationTable.filepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Station arrival = Algorithm.<Station>find(stations, (e) -> {
            return e.id == stationArrivalId;
        });

        Station departure = Algorithm.<Station>find(stations, (e) -> {
            return e.id == stationDepartureId;
        });

        if (Algorithm.<Account>exists(accounts, (e) -> {
            return e.id == accountId;
        }) && arrival != null && departure != null) {
            Bus bus = new Bus(name, facilities, new Price(price), capacity, busType, departure, arrival, accountId);

            busTable.add(bus);

            return new BaseResponse<>(true, "Successful", bus);
        }

        return new BaseResponse<>(false, "Unsuccessful", null);
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

    @GetMapping("/getMyBus")
    public List<Bus> getMyBus(@RequestParam int accountId) {
        return Algorithm.<Bus>collect(getJsonTable(), b->b.accountId==accountId);
    }
}
