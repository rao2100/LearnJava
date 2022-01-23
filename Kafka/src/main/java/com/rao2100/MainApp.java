package com.rao2100;

import com.rao2100.producer.ProducerJsonUser;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;



public class MainApp {
    
    public static void main(String[] args) {

        System.out.println("MainApp");
        ProducerJsonUser producerJsonUser = new ProducerJsonUser();
        producerJsonUser.run();

    }    
    
}

