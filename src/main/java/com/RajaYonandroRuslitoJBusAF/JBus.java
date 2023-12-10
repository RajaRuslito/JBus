package com.RajaYonandroRuslitoJBusAF;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Timestamp;
import java.text.*;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.RajaYonandroRuslitoJBusAF.dbjson.JsonDBEngine;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for the JBus application.
 * Initializes and runs the JsonDBEngine for database management.
 * Uses Spring Boot for application setup and execution.
 */
@SpringBootApplication
public class JBus {

    /**
     * The main method that initializes and runs the JBus application.
     *
     * @param args Command line arguments (not used in this application).
     * @throws InterruptedException Thrown when a thread is interrupted.
     */
    public static void main(String[] args) throws InterruptedException {

        JsonDBEngine.Run(JBus.class);
        SpringApplication.run(JBus.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));


    }


}