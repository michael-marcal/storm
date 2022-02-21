package com.michaelmarcal.commons.storm.observation;

import com.michaelmarcal.commons.storm.alert.Alert;
import com.michaelmarcal.commons.storm.alert.AlertFactory;
import com.michaelmarcal.commons.storm.alert.AlertType;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrecipitationTest {

    @Test
    public void testPrecipitation( ) {

        Precipitation precipitation = new Precipitation();
        LocalDateTime now = LocalDateTime.now();

        List<Alert> alerts = precipitation.addPrecipitationReading( now, 0.0);
        assertEquals(0, alerts.size());
        assertAll(
                ()->assertEquals(0.0, precipitation.getLatestObservationValue(), .001),
                ()->assertEquals(0.0, precipitation.getMaximumObservationValue(), .001),
                ()->assertEquals(0.0, precipitation.getMinimumObservationValue(), .001),
                ()->assertEquals(0.0, precipitation.getLatestPrecipitationRate(), .001),
                ()->assertEquals(0.0, precipitation.getMaximumPrecipitationRate(), .001),
                ()->assertEquals(0.0, precipitation.getMinimumPrecipitationRate(), .001)
        );

        alerts = precipitation.addPrecipitationReading( now.plusMinutes(15L), 0.2);
        assertEquals(0, alerts.size());
        assertAll(
                ()->assertEquals(0.2, precipitation.getLatestObservationValue(), .001),
                ()->assertEquals(0.2, precipitation.getMaximumObservationValue(), .001),
                ()->assertEquals(0.0, precipitation.getMinimumObservationValue(), .001),
                ()->assertEquals(0.2, precipitation.getLatestPrecipitationRate(), .001),
                ()->assertEquals(0.2, precipitation.getMaximumPrecipitationRate(), .001),
                ()->assertEquals(0.0, precipitation.getMinimumPrecipitationRate(), .001)
        );

        alerts = precipitation.addPrecipitationReading( now.plusMinutes(15L), 0.3);
        assertEquals(0, alerts.size());
        assertAll(
                ()->assertEquals(0.3, precipitation.getLatestObservationValue(), .001),
                ()->assertEquals(0.3, precipitation.getMaximumObservationValue(), .001),
                ()->assertEquals(0.0, precipitation.getMinimumObservationValue(), .001),
                ()->assertEquals(0.1, precipitation.getLatestPrecipitationRate(), .001),
                ()->assertEquals(0.2, precipitation.getMaximumPrecipitationRate(), .001),
                ()->assertEquals(0.0, precipitation.getMinimumPrecipitationRate(), .001)
        );
    }

    @Test
    public void testPrecipitationAlerts() {
        Precipitation precipitation = new Precipitation();
        AlertFactory alertFactory = new AlertFactory();
        precipitation.addAlert( alertFactory.getExceedsThresholdAlert(AlertType.PRECIPITATION, 10.0, "High precipitation"));
        LocalDateTime now = LocalDateTime.now();

        List<Alert> alerts = precipitation.addPrecipitationReading( now, 0.0);
        assertEquals(0, alerts.size());
        assertAll(
                ()->assertEquals(0, precipitation.getActiveAlerts().size()),
                ()->assertEquals(0, precipitation.getHistoricalAlerts().size())
        );

        alerts = precipitation.addPrecipitationReading( now, 11.0);
        assertEquals(1, alerts.size());
        assertAll(
                ()->assertEquals(1, precipitation.getActiveAlerts().size()),
                ()->assertEquals(1, precipitation.getHistoricalAlerts().size())
        );

        alerts = precipitation.addPrecipitationReading( now, 0.0);
        assertEquals(0, alerts.size());
        assertAll(
                ()->assertEquals(0, precipitation.getActiveAlerts().size()),
                ()->assertEquals(1, precipitation.getHistoricalAlerts().size())
        );
    }

    @Test
    public void testIllegalAlertAddException() {
        AlertFactory alertFactory = new AlertFactory();
        Alert windAlert = alertFactory.getExceedsThresholdAlert(AlertType.WIND, 10.0, "High Wind");

        Precipitation precipitation = new Precipitation();
        assertThrows( IllegalArgumentException.class, ()-> precipitation.addAlert(windAlert));
    }
}
