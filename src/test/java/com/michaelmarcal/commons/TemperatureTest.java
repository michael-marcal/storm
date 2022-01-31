package com.michaelmarcal.commons;

import com.michaelmarcal.commons.storm.composition.Temperature;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class TemperatureTest {

    @Test
    public void TestTemperature() {
        Temperature temp = new Temperature();
        LocalDateTime now = LocalDateTime.now();

        temp.addTemperaturePoint(now, 10.0);
        Assert.assertEquals(Double.valueOf(10.0), temp.getLatestTemperature());
        Assert.assertEquals(Double.valueOf(10.0), temp.getMaximumTemperature());
        Assert.assertEquals(Double.valueOf(10.0), temp.getMinimumTemperature());

        temp.addTemperaturePoint(now.plusMinutes(15L), 11.0);
        Assert.assertEquals(Double.valueOf(11.0), temp.getLatestTemperature());
        Assert.assertEquals(Double.valueOf(11.0), temp.getMaximumTemperature());
        Assert.assertEquals(Double.valueOf(10.0), temp.getMinimumTemperature());

        temp.addTemperaturePoint(now.plusMinutes(15L), 12.0);
        temp.addTemperaturePoint(now.plusMinutes(15L), 13.0);
        temp.addTemperaturePoint(now.plusMinutes(15L), 14.0);
        Assert.assertEquals(Double.valueOf(14.0), temp.getLatestTemperature());
        Assert.assertEquals(Double.valueOf(14.0), temp.getMaximumTemperature());
        Assert.assertEquals(Double.valueOf(10.0), temp.getMinimumTemperature());

        temp.addTemperaturePoint(now.plusMinutes(15L), 8.0);
        Assert.assertEquals(Double.valueOf(8.0), temp.getMinimumTemperature());
    }
}
