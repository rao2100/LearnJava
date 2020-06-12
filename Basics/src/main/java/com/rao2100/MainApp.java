package com.rao2100;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import com.rao2100.enums.*;
import com.rao2100.datetime.*;
import com.rao2100.nashorn.*;
import com.rao2100.abstraction.*;

public class MainApp {
    
    public static void main(String[] args) {

        // TestEnum.test();
        // TestDateTime.testAllVals();
        // TestNashorn testNashorn = new TestNashorn();
        // testNashorn.runScript();       
        
        TestAbstraction testAbstraction = new TestAbstraction();
        testAbstraction.runTest();;
    }    
    
}

