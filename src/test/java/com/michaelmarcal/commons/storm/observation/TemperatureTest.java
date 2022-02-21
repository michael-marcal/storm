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

public class TemperatureTest {

    @Test
    public void TestTemperature() {
        Temperature temp = new Temperature();
        LocalDateTime now = LocalDateTime.now();

        List<Alert> alerts = temp.addTemperaturePoint(now, 10.0);
        assertEquals(0, alerts.size());
        assertAll(
                ()->assertEquals(Double.valueOf(10.0), temp.getLatestObservationValue()),
                ()->assertEquals(Double.valueOf(10.0), temp.getMaximumObservationValue()),
                ()->assertEquals(Double.valueOf(10.0), temp.getMinimumObservationValue())
        );

        alerts = temp.addTemperaturePoint(now.plusMinutes(15L), 11.0);
        assertEquals(0, alerts.size());
        assertAll(
                ()->assertEquals(Double.valueOf(11.0), temp.getLatestObservationValue()),
                ()->assertEquals(Double.valueOf(11.0), temp.getMaximumObservationValue()),
                ()->assertEquals(Double.valueOf(10.0), temp.getMinimumObservationValue())
        );

        alerts = temp.addTemperaturePoint(now.plusMinutes(15L), 12.0);
        assertEquals(0, alerts.size());
        alerts = temp.addTemperaturePoint(now.plusMinutes(15L), 13.0);
        assertEquals(0, alerts.size());
        alerts = temp.addTemperaturePoint(now.plusMinutes(15L), 14.0);
        assertEquals(0, alerts.size());
        assertAll(
                ()->assertEquals(Double.valueOf(14.0), temp.getLatestObservationValue()),
                ()->assertEquals(Double.valueOf(14.0), temp.getMaximumObservationValue()),
                ()->assertEquals(Double.valueOf(10.0), temp.getMinimumObservationValue())
        );

        alerts = temp.addTemperaturePoint(now.plusMinutes(15L), 8.0);
        assertEquals(0, alerts.size());
        assertEquals(Double.valueOf(8.0), temp.getMinimumObservationValue());
    }

    @Test
    public void testTemperatureAlerts() {
        Temperature temp = new Temperature();
        LocalDateTime now = LocalDateTime.now();
        AlertFactory alertFactory = new AlertFactory();
        temp.addAlert( alertFactory.getExceedsThresholdAlert(AlertType.TEMPERATURE, 90.0, "High temperature" ));

        List<Alert> alerts = temp.addTemperaturePoint(now, 10.0);
        assertEquals(0, alerts.size());
        assertAll(
                ()->assertEquals(0, temp.getActiveAlerts().size()),
                ()->assertEquals(0, temp.getHistoricalAlerts().size())
        );

        alerts = temp.addTemperaturePoint(now, 100.0);
        assertEquals(1, alerts.size());
        assertAll(
                ()->assertEquals(1, temp.getActiveAlerts().size()),
                ()->assertEquals(1, temp.getHistoricalAlerts().size())
        );

        alerts = temp.addTemperaturePoint(now, 80.0);
        assertEquals(0, alerts.size());
        assertAll(
                ()->assertEquals(0, temp.getActiveAlerts().size()),
                ()->assertEquals(1, temp.getHistoricalAlerts().size())
        );
    }

    @Test
    public void testIllegalAlertAddException() {
        AlertFactory alertFactory = new AlertFactory();
        Alert windAlert = alertFactory.getExceedsThresholdAlert(AlertType.WIND, 10.0, "High Wind");

        Temperature temp = new Temperature();
        assertThrows( IllegalArgumentException.class, ()->temp.addAlert(windAlert) );
    }
}
