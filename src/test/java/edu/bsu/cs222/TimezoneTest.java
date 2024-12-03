package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimezoneTest {

    @Test
    public void getTimezoneTest(){
        Date date = new Date();
        Timezone timezone = new Timezone();
        double lat = 40.191898;
        double lon = -85.410027;
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa (z)");
        Assertions.assertEquals(timeFormat.format(date), timezone.getTimezone(lat, lon));
    }
}
