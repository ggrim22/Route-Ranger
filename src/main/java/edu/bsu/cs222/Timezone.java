package edu.bsu.cs222;
import net.iakovlev.timeshape.TimeZoneEngine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

public class Timezone {

    protected String getTimezone(double latitude, double longitude) {
        TimeZoneEngine engine = TimeZoneEngine.initialize();
        Optional<ZoneId> tz = engine.query(latitude, longitude);
        String zoneText = tz.map(ZoneId::toString).orElse("Default/Zone");
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("hh:mm");
        df.setTimeZone(TimeZone.getTimeZone(zoneText));
        return df.format(date);
    }

}
