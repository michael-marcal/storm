package com.michaelmarcal.commons.storm.observation;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeriesPointTest {

    @Test
    public void testSeriesPoint() {
        LocalDateTime now = LocalDateTime.now();
        Double val = 10.2;
        SeriesPoint sp = new SeriesPoint( now, val );

        LocalDateTime spTime = sp.getTimepoint();
        Double spValue = sp.getValue();

        assertAll(
                ()->assertEquals(now, spTime),
                ()->assertEquals(val, spValue)
        );
    }

    @Test
    public void testSeriesPointDirectional() {
        LocalDateTime now = LocalDateTime.now();
        Double val = 10.2;
        Double dir = 15.2;
        SeriesPointDirectional sp = new SeriesPointDirectional(now, val, dir);

        LocalDateTime spTime = sp.getTimepoint();
        Double spValue = sp.getValue();
        Double spDir = sp.getDirection();

        assertAll(
                ()->assertEquals(now, spTime),
                ()->assertEquals(val, spValue),
                ()->assertEquals(dir, spDir)
        );
    }
}
