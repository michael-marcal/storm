package com.michaelmarcal.commons.storm.composition;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class AlertFactoryTest {

    @Test
    public void test_AlertFactory() {
        AlertFactory factory = new AlertFactory();
        LocalDateTime now = LocalDateTime.now();

        Alert windExceedAlert = factory.getExceedsThresholdAlert(AlertType.WIND, 10.0, "High Wind");
        Alert windLessThanAlert = factory.getLessThanThresholdAlert(AlertType.WIND, -15.0, "Low Wind" );

        Assert.assertEquals(AlertType.WIND, windExceedAlert.getType());
        Assert.assertEquals(true, windExceedAlert.evaluateAlert(new SeriesPoint( now, 15)));
        Assert.assertEquals(false, windExceedAlert.evaluateAlert(new SeriesPoint( now, 9)));
        Assert.assertEquals(false, windExceedAlert.evaluateAlert(new SeriesPoint( now, 10.0)));

        Assert.assertEquals(AlertType.WIND, windLessThanAlert.getType());
        Assert.assertEquals(true, windLessThanAlert.evaluateAlert(new SeriesPoint( now, -20.0)));
        Assert.assertEquals(false, windLessThanAlert.evaluateAlert(new SeriesPoint( now, -15.0)));
        Assert.assertEquals(false, windLessThanAlert.evaluateAlert(new SeriesPoint( now, -10.0)));
    }
}
