package com.michaelmarcal.commons.storm.composition;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class SeriesPointTest {

    @Test
    public void testSeriesPoint() {
        LocalDateTime now = LocalDateTime.now();
        Double val = 10.2;
        SeriesPoint sp = new SeriesPoint( now, val );

        LocalDateTime spTime = sp.getTimepoint();
        Double spValue = sp.getValue();

        Assert.assertEquals(now, spTime);
        Assert.assertEquals(val, spValue);
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

        Assert.assertEquals(now, spTime);
        Assert.assertEquals(val, spValue);
        Assert.assertEquals(dir, spDir);
    }
}
