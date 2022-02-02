package com.michaelmarcal.commons.storm.composition;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class WindTest {

    @Test
    public void testWind() {
        Wind wind = new Wind();
        LocalDateTime now = LocalDateTime.now();

        wind.addWindReading(now, 10.0, 15.0);
        Assert.assertEquals(Double.valueOf(10.0), wind.getLatestWindSpeed(), .001);
        Assert.assertEquals(Double.valueOf(15.0), wind.getLatestWindDirection(), .001);
        Assert.assertEquals(Double.valueOf(10.0), wind.getMaximumWindSpeed(), .001);
        Assert.assertEquals(Double.valueOf(10.0), wind.getMinimumWindSpeed(), .001);

        wind.addWindReading(now, 15.0, 20.0);
        Assert.assertEquals(Double.valueOf(15.0), wind.getLatestWindSpeed(), .001);
        Assert.assertEquals(Double.valueOf(20.0), wind.getLatestWindDirection(), .001);
        Assert.assertEquals(Double.valueOf(15.0), wind.getMaximumWindSpeed(), .001);
        Assert.assertEquals(Double.valueOf(10.0), wind.getMinimumWindSpeed(), .001);
    }
}
