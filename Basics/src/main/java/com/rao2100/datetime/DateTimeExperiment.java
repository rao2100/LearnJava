
package com.rao2100.datetime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class DateTimeExperiment {


    private static String getDate(LocalDateTime dateBefore) {
        
        return dateBefore.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private static String getDateRetentionMin(List<String> functionInput) {

        LocalDateTime dateBefore = LocalDateTime.of(2019, 12, 31, 00, 00);  
        System.out.println(dateBefore);
        Long timeValue = Long.parseLong(functionInput.get(1).trim());

        return dateBefore.format(DateTimeFormatter.ofPattern(functionInput.get(3).trim()));
    }

    public static void getWindowBoundries(){

        // Duration size = Duration.ofMinutes(5);
        // Duration size = Duration.ofHours(1);
        Duration size = Duration.ofHours(24);
        // Duration size = Duration.ofDays(1);

        long sizeMs = size.toMillis();
        long timestamp = Instant.now().toEpochMilli();

        ArrayList<ZoneId> zones = new ArrayList<>();
        // zones.add(ZoneId.of("America/Los_Angeles"));
        // zones.add(ZoneId.of("UTC"));
        // zones.add(ZoneId.of("Europe/Dublin"));
        zones.add(ZoneId.systemDefault());
        zones.add(ZoneId.of("Asia/Tokyo"));


        System.out.println("unixTime: " + timestamp);
        System.out.println(Instant.ofEpochMilli(timestamp));

        for (ZoneId zoneId : zones) {
            long offset = getOffset(timestamp, zoneId);
            long startMs = (( Math.max(0, offset + timestamp) / sizeMs) * sizeMs) - offset;
            long endMs = (startMs + sizeMs) - 1;
            System.out.println("zoneId: " + zoneId + " offset: " + offset);
            System.out.println("startMs: " + startMs);
            System.out.println(Instant.ofEpochMilli(startMs));
            System.out.println("endMs: " + endMs);
            System.out.println(Instant.ofEpochMilli(endMs));
            long timestampAdj = timestamp + offset;
            System.out.println("unixTime: " + timestampAdj);
            System.out.println(Instant.ofEpochMilli(timestampAdj));
            
        }

        
        
        // // long timestampWithOffset = addOffset(timestamp);
        // // long startMs = timestampWithOffset - (timestampWithOffset % sizeMs);        
        // // long startMs = timestamp - (timestamp % sizeMs);
        // long startMs = getBounds(timestamp, sizeMs);
             
        // // long endMs = (startMs + sizeMs) - 1;
        // long startMsWithOffset = startMs;
        // if (sizeMs >= Duration.ofHours(24).toMillis()){
        //     startMsWithOffset = addOffset(startMs);
        // }
        
        // // long endMsWithOffset = addOffset(endMs);
        // // long endMsWithOffset = (startMsWithOffset + sizeMs) - 1;
        // long offset = getOffset(timestamp);
        // long advanceMs = sizeMs;
        // // long windowStart = (( Math.max(0, offset + timestamp - sizeMs + advanceMs) / advanceMs) * advanceMs) - offset;
        // long windowStart = (( Math.max(0, offset + timestamp) / sizeMs) * sizeMs) - offset;
        // System.out.println("windowStart: " + windowStart);
        // System.out.println(Instant.ofEpochMilli(windowStart));
        
        // System.out.println("unixTime: " + timestamp);
        // System.out.println(Instant.ofEpochMilli(timestamp));
        // System.out.println("System.currentTimeMillis(): " + System.currentTimeMillis());
        // System.out.println(Instant.ofEpochMilli(System.currentTimeMillis()));
        

        // // long endMs = (timestamp/sizeMs) * sizeMs - 1;
        // // System.out.println("unixTime: " + timestamp);
        // // System.out.println(Instant.ofEpochMilli(timestamp));


        // // System.out.println("timestampWithOffset: " + timestampWithOffset);
        // // System.out.println(Instant.ofEpochMilli(timestampWithOffset));
        // System.out.println("startMs: " + startMs);
        // System.out.println(Instant.ofEpochMilli(startMs));
        // // System.out.println("endMs: " + endMs);
        // // System.out.println(Instant.ofEpochMilli(endMs));

        // System.out.println("startMsWithOffset: " + Instant.ofEpochMilli(startMsWithOffset));  
        // // System.out.println("endMsWithOffset: " + Instant.ofEpochMilli(endMsWithOffset));
        // System.out.println(startMsWithOffset);
        // // System.out.println(endMsWithOffset);

    }


    private static long getBounds(long timestamp, long sizeMs){

        return timestamp - (timestamp % sizeMs);
    }

    private static long addOffset(long timestamp){

        Instant instant = Instant.ofEpochMilli(timestamp);
        
        ZoneId zid = ZoneId.of("America/Los_Angeles");
        // ZoneId zid = ZoneId.of("US/Mountain");
        // ZoneId zid = ZoneId.of("America/Chicago");
        // ZoneId zid = ZoneId.systemDefault();
        // ZoneId zid = ZoneId.of("Europe/Dublin");
        
        
        long offsetUTC = zid.getRules().getOffset(instant).getTotalSeconds() * 1000L;
        System.out.println("Rao: windows boundaries ZoneId.systemDefault(): {} " + zid);        
        System.out.println("Rao: windows boundaries offsetUTC: {} "+ offsetUTC);        
        return timestamp - offsetUTC;
    }


    private static long getOffset(long timestamp, ZoneId zid){

        Instant instant = Instant.ofEpochMilli(timestamp);
        
        // ZoneId zid = ZoneId.of("America/Los_Angeles");
        // ZoneId zid = ZoneId.of("US/Mountain");
        // ZoneId zid = ZoneId.of("America/Chicago");
        // ZoneId zid = ZoneId.of("Europe/Dublin");
        // ZoneId zid = ZoneId.systemDefault();
        
        
        
        long offsetUTC = zid.getRules().getOffset(instant).getTotalSeconds() * 1000L;        
        System.out.println("Rao: windows boundaries ZoneId.systemDefault(): {} " + zid);        
        System.out.println("Rao: windows boundaries offsetUTC: {} "+ offsetUTC);        
        return offsetUTC;
    }

}