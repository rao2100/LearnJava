
package com.rao2100.datetime;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class TestDateTime {

    public static void testAllVals() {
        
        LocalDateTime dateBefore = LocalDateTime.of(2020, 06, 30, 00, 00);
        for (int i = 0; i < 365; i++) {
            System.out.println(getDate(dateBefore.minusDays(i)));            
        }      
        
    }

    private static String getDate(LocalDateTime dateBefore) {
        
        return dateBefore.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private static String getDateRetentionMin(List<String> functionInput) {

        LocalDateTime dateBefore = LocalDateTime.of(2019, 12, 31, 00, 00);  
        System.out.println(dateBefore);
        Long timeValue = Long.parseLong(functionInput.get(1).trim());

        return dateBefore.format(DateTimeFormatter.ofPattern(functionInput.get(3).trim()));
    }

}