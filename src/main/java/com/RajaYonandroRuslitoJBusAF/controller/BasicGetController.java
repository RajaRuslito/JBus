package com.RajaYonandroRuslitoJBusAF.controller;

import com.RajaYonandroRuslitoJBusAF.Algorithm;
import com.RajaYonandroRuslitoJBusAF.dbjson.JsonTable;
import com.RajaYonandroRuslitoJBusAF.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public interface BasicGetController <T extends Serializable> {

    public JsonTable<T> getJsonTable();

    @GetMapping("/page")
    public default List<T> getPage(int page, int pagesize){
        return Algorithm.<T>paginate(getJsonTable(), page, pagesize, (e) -> {
            return true;
        });
    }

    @GetMapping("/{id}")
    public default T getById(@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(), (e) -> {
            return e.id == id;
        });
    }

}
