package com.michaelmarcal.commons.storm.composition;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class TemperatureTest {

    @Test
    public void TestTemperature() {
        Temperature temp = new Temperature();
        LocalDateTime now = LocalDateTime.now();

        List<Alert> alerts = temp.addTemperaturePoint(now, 10.0);
        Assert.assertEquals(0, alerts.size());
        Assert.assertEquals(Double.valueOf(10.0), temp.getLatestTemperature());
        Assert.assertEquals(Double.valueOf(10.0), temp.getMaximumTemperature());
        Assert.assertEquals(Double.valueOf(10.0), temp.getMinimumTemperature());

        alerts = temp.addTemperaturePoint(now.plusMinutes(15L), 11.0);
        Assert.assertEquals(0, alerts.size());
        Assert.assertEquals(Double.valueOf(11.0), temp.getLatestTemperature());
        Assert.assertEquals(Double.valueOf(11.0), temp.getMaximumTemperature());
        Assert.assertEquals(Double.valueOf(10.0), temp.getMinimumTemperature());

        alerts = temp.addTemperaturePoint(now.plusMinutes(15L), 12.0);
        Assert.assertEquals(0, alerts.size());
        alerts = temp.addTemperaturePoint(now.plusMinutes(15L), 13.0);
        Assert.assertEquals(0, alerts.size());
        alerts = temp.addTemperaturePoint(now.plusMinutes(15L), 14.0);
        Assert.assertEquals(0, alerts.size());
        Assert.assertEquals(Double.valueOf(14.0), temp.getLatestTemperature());
        Assert.assertEquals(Double.valueOf(14.0), temp.getMaximumTemperature());
        Assert.assertEquals(Double.valueOf(10.0), temp.getMinimumTemperature());

        alerts = temp.addTemperaturePoint(now.plusMinutes(15L), 8.0);
        Assert.assertEquals(0, alerts.size());
        Assert.assertEquals(Double.valueOf(8.0), temp.getMinimumTemperature());
    }

    @Test
    public void testTemperatureAlerts() {
        Temperature temp = new Temperature();
        LocalDateTime now = LocalDateTime.now();
        AlertFactory alertFactory = new AlertFactory();
        temp.addAlert( alertFactory.getExceedsThresholdAlert(AlertType.TEMPERATURE, 90.0, "High temperature" ));

        List<Alert> alerts = temp.addTemperaturePoint(now, 10.0);
        Assert.assertEquals(0, alerts.size());
        Assert.assertEquals(0, temp.getActiveAlerts().size());
        Assert.assertEquals(0, temp.getHistoricalAlerts().size());

        alerts = temp.addTemperaturePoint(now, 100.0);
        Assert.assertEquals(1, alerts.size());
        Assert.assertEquals(1, temp.getActiveAlerts().size());
        Assert.assertEquals(1, temp.getHistoricalAlerts().size());

        alerts = temp.addTemperaturePoint(now, 80.0);
        Assert.assertEquals(0, alerts.size());
        Assert.assertEquals(0, temp.getActiveAlerts().size());
        Assert.assertEquals(1, temp.getHistoricalAlerts().size());
    }
}
