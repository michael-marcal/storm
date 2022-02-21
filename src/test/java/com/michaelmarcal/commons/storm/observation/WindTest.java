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

public class WindTest {

    @Test
    public void testWind() {
        Wind wind = new Wind();
        LocalDateTime now = LocalDateTime.now();

        List<Alert> alerts = wind.addWindReading(now, 10.0, 15.0);
        assertEquals(0, alerts.size());
        assertAll(
                ()->assertEquals(10.0, wind.getLatestObservationValue(), .001),
                ()->assertEquals(15.0, wind.getLatestWindDirection(), .001),
                ()->assertEquals(10.0, wind.getMaximumObservationValue(), .001),
                ()->assertEquals(10.0, wind.getMinimumObservationValue(), .001)
        );

        wind.addWindReading(now, 15.0, 20.0);
        assertEquals(0, alerts.size());
        assertAll(
                ()->assertEquals(15.0, wind.getLatestObservationValue(), .001),
                ()->assertEquals(20.0, wind.getLatestWindDirection(), .001),
                ()->assertEquals(15.0, wind.getMaximumObservationValue(), .001),
                ()->assertEquals(10.0, wind.getMinimumObservationValue(), .001)
        );
    }

    @Test
    public void testWindAlerts() {
        Wind wind = new Wind();
        LocalDateTime now = LocalDateTime.now();
        AlertFactory alertFactory = new AlertFactory();
        wind.addAlert( alertFactory.getExceedsThresholdAlert(AlertType.WIND, 10.0, "High Wind" ));

        List<Alert> alerts = wind.addWindReading(now, 10.0, 15.0);
        assertEquals(0, alerts.size());
        assertAll(
                ()->assertEquals(0, wind.getActiveAlerts().size()),
                ()->assertEquals(0, wind.getHistoricalAlerts().size())
        );

        alerts = wind.addWindReading(now, 20.0, 15.0);
        assertEquals(1, alerts.size());
        assertAll(
                ()->assertEquals(1, wind.getActiveAlerts().size()),
                ()->assertEquals(1, wind.getHistoricalAlerts().size())
        );

        alerts = wind.addWindReading(now, 9.0, 15.0);
        assertEquals(0, alerts.size());
        assertAll(
                ()->assertEquals(0, wind.getActiveAlerts().size()),
                ()->assertEquals(1, wind.getHistoricalAlerts().size())
        );
    }

    @Test
    public void testIllegalAlertAddException() {
        AlertFactory alertFactory = new AlertFactory();
        Alert tempAlert = alertFactory.getExceedsThresholdAlert(AlertType.TEMPERATURE, 90.0, "High Temp");

        Wind wind = new Wind();
        assertThrows( IllegalArgumentException.class, ()->wind.addAlert( tempAlert ));
    }
}
