package com.michaelmarcal.commons.storm.composition;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class WindTest {

    @Test
    public void testWind() {
        Wind wind = new Wind();
        LocalDateTime now = LocalDateTime.now();

        List<Alert> alerts = wind.addWindReading(now, 10.0, 15.0);
        Assert.assertEquals(0, alerts.size());
        Assert.assertEquals(10.0, wind.getLatestWindSpeed(), .001);
        Assert.assertEquals(15.0, wind.getLatestWindDirection(), .001);
        Assert.assertEquals(10.0, wind.getMaximumWindSpeed(), .001);
        Assert.assertEquals(10.0, wind.getMinimumWindSpeed(), .001);

        wind.addWindReading(now, 15.0, 20.0);
        Assert.assertEquals(0, alerts.size());
        Assert.assertEquals(15.0, wind.getLatestWindSpeed(), .001);
        Assert.assertEquals(20.0, wind.getLatestWindDirection(), .001);
        Assert.assertEquals(15.0, wind.getMaximumWindSpeed(), .001);
        Assert.assertEquals(10.0, wind.getMinimumWindSpeed(), .001);
    }

    @Test
    public void testWindAlerts() {
        Wind wind = new Wind();
        LocalDateTime now = LocalDateTime.now();
        AlertFactory alertFactory = new AlertFactory();
        wind.addAlert( alertFactory.getExceedsThresholdAlert(AlertType.WIND, 10.0, "High Wind" ));

        List<Alert> alerts = wind.addWindReading(now, 10.0, 15.0);
        Assert.assertEquals(0, alerts.size());
        Assert.assertEquals(0, wind.getActiveAlerts().size());
        Assert.assertEquals(0, wind.getHistoricalAlerts().size());

        alerts = wind.addWindReading(now, 20.0, 15.0);
        Assert.assertEquals(1, alerts.size());
        Assert.assertEquals(1, wind.getActiveAlerts().size());
        Assert.assertEquals(1, wind.getHistoricalAlerts().size());

        alerts = wind.addWindReading(now, 9.0, 15.0);
        Assert.assertEquals(0, alerts.size());
        Assert.assertEquals(0, wind.getActiveAlerts().size());
        Assert.assertEquals(1, wind.getHistoricalAlerts().size());
    }
}
