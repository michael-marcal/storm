package com.michaelmarcal.commons.storm.alert;

import com.michaelmarcal.commons.storm.observation.SeriesPoint;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertFactoryTest {

    @Test
    public void test_AlertFactory() {
        AlertFactory factory = new AlertFactory();
        LocalDateTime now = LocalDateTime.now();

        Alert windExceedAlert = factory.getExceedsThresholdAlert(AlertType.WIND, 10.0, "High Wind");
        Alert windLessThanAlert = factory.getLessThanThresholdAlert(AlertType.WIND, -15.0, "Low Wind" );

        assertAll(
            ()->assertEquals(AlertType.WIND, windExceedAlert.getType()),
            ()->assertEquals(true, windExceedAlert.evaluateAlert(new SeriesPoint( now, 15))),
            ()->assertEquals(false, windExceedAlert.evaluateAlert(new SeriesPoint( now, 9))),
            ()->assertEquals(false, windExceedAlert.evaluateAlert(new SeriesPoint( now, 10.0))),

            ()->assertEquals(AlertType.WIND, windLessThanAlert.getType()),
            ()->assertEquals(true, windLessThanAlert.evaluateAlert(new SeriesPoint( now, -20.0))),
            ()->assertEquals(false, windLessThanAlert.evaluateAlert(new SeriesPoint( now, -15.0))),
            ()->assertEquals(false, windLessThanAlert.evaluateAlert(new SeriesPoint( now, -10.0)))
        );
    }
}
