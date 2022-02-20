package com.michaelmarcal.commons.storm.composition;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class PrecipitationTest {

    @Test
    public void testPrecipitation( ) {

        Precipitation precipitation = new Precipitation();
        LocalDateTime now = LocalDateTime.now();

        List<Alert> alerts = precipitation.addPrecipitationReading( now, 0.0);
        Assert.assertEquals(0, alerts.size());
        Assert.assertEquals(0.0, precipitation.getLatestPrecipitation(), .001);
        Assert.assertEquals(0.0, precipitation.getMaximumPrecipitation(), .001);
        Assert.assertEquals(0.0, precipitation.getMinimumPrecipitation(), .001);
        Assert.assertEquals(0.0, precipitation.getLatestPrecipitationRate(), .001);
        Assert.assertEquals(0.0, precipitation.getMaximumPrecipitationRate(), .001);
        Assert.assertEquals(0.0, precipitation.getMinimumPrecipitationRate(), .001);

        alerts = precipitation.addPrecipitationReading( now.plusMinutes(15L), 0.2);
        Assert.assertEquals(0, alerts.size());
        Assert.assertEquals(0.2, precipitation.getLatestPrecipitation(), .001);
        Assert.assertEquals(0.2, precipitation.getMaximumPrecipitation(), .001);
        Assert.assertEquals(0.0, precipitation.getMinimumPrecipitation(), .001);
        Assert.assertEquals(0.2, precipitation.getLatestPrecipitationRate(), .001);
        Assert.assertEquals(0.2, precipitation.getMaximumPrecipitationRate(), .001);
        Assert.assertEquals(0.0, precipitation.getMinimumPrecipitationRate(), .001);

        alerts = precipitation.addPrecipitationReading( now.plusMinutes(15L), 0.3);
        Assert.assertEquals(0, alerts.size());
        Assert.assertEquals(0.3, precipitation.getLatestPrecipitation(), .001);
        Assert.assertEquals(0.3, precipitation.getMaximumPrecipitation(), .001);
        Assert.assertEquals(0.0, precipitation.getMinimumPrecipitation(), .001);
        Assert.assertEquals(0.1, precipitation.getLatestPrecipitationRate(), .001);
        Assert.assertEquals(0.2, precipitation.getMaximumPrecipitationRate(), .001);
        Assert.assertEquals(0.0, precipitation.getMinimumPrecipitationRate(), .001);
    }

    @Test
    public void testPrecipitationAlerts() {
        Precipitation precipitation = new Precipitation();
        AlertFactory alertFactory = new AlertFactory();
        precipitation.addAlert( alertFactory.getExceedsThresholdAlert(AlertType.PRECIPITATION, 10.0, "High precipitation"));
        LocalDateTime now = LocalDateTime.now();

        List<Alert> alerts = precipitation.addPrecipitationReading( now, 0.0);
        Assert.assertEquals(0, alerts.size());
        Assert.assertEquals(0, precipitation.getActiveAlerts().size());
        Assert.assertEquals(0, precipitation.getHistoricalAlerts().size());

        alerts = precipitation.addPrecipitationReading( now, 11.0);
        Assert.assertEquals(1, alerts.size());
        Assert.assertEquals(1, precipitation.getActiveAlerts().size());
        Assert.assertEquals(1, precipitation.getHistoricalAlerts().size());

        alerts = precipitation.addPrecipitationReading( now, 0.0);
        Assert.assertEquals(0, alerts.size());
        Assert.assertEquals(0, precipitation.getActiveAlerts().size());
        Assert.assertEquals(1, precipitation.getHistoricalAlerts().size());
    }
}
