package com.michaelmarcal.commons.storm.composition;

import com.michaelmarcal.commons.storm.alert.Alert;
import com.michaelmarcal.commons.storm.alert.AlertFactory;
import com.michaelmarcal.commons.storm.alert.AlertType;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StormTest {

    @Test
    public void test_Storm() {
        Storm storm = new Storm();
        AlertFactory alertFactory = new AlertFactory();
        LocalDateTime now = LocalDateTime.now();

        Alert windAlert = alertFactory.getExceedsThresholdAlert(AlertType.WIND, 10.0, "High wind" );
        Alert tempAlert = alertFactory.getExceedsThresholdAlert(AlertType.TEMPERATURE, 90.0, "High temp");
        Alert precipitationAlert = alertFactory.getExceedsThresholdAlert(AlertType.PRECIPITATION, 2.0, "High precipitation");
        storm.addAlert(windAlert);
        storm.addAlert(tempAlert);
        storm.addAlert(precipitationAlert);

        Assert.assertEquals(3, storm.getAlerts().size());

        List<Alert> activeAlerts = storm.addReadings(now, 10.0, 5.0, 33.0, 0.0);
        Assert.assertEquals(0, activeAlerts.size());

        activeAlerts = storm.addReadings(11.0, 5.1, 32.0, 0.0);
        Assert.assertEquals(1, activeAlerts.size());

    }

    @Test
    public void test_AddAlerts(){
        Storm storm = new Storm();
        AlertFactory alertFactory = new AlertFactory();

        Alert windAlert = alertFactory.getExceedsThresholdAlert(AlertType.WIND, 10.0, "High wind" );
        Alert tempAlert = alertFactory.getExceedsThresholdAlert(AlertType.TEMPERATURE, 90.0, "High temp");
        Alert precipitationAlert = alertFactory.getExceedsThresholdAlert(AlertType.PRECIPITATION, 2.0, "High precipitation");
        storm.addAlert(windAlert);
        storm.addAlert(tempAlert);
        storm.addAlert(precipitationAlert);

        List<Alert> alerts = storm.getAlerts();
        Assert.assertEquals(3, alerts.size());
        Assert.assertEquals(1,
                alerts.stream().filter(a->a.getName().equals("High wind")).collect(Collectors.toSet()).size());
        Assert.assertEquals(1,
                alerts.stream().filter(a->a.getName().equals("High temp")).collect(Collectors.toSet()).size());
        Assert.assertEquals(1,
                alerts.stream().filter(a->a.getName().equals("High precipitation")).collect(Collectors.toSet()).size());
    }

    @Test
    public void test_ActiveAlerts(){
        Storm storm = new Storm();
        LocalDateTime now = LocalDateTime.now();
        AlertFactory alertFactory = new AlertFactory();

        // no alerts
        List<Alert> activeAlerts =storm.addReadings(now, 10.0, 5.0, 33.0, 0.0);
        Assert.assertEquals(0, activeAlerts.size());

        // temperature alert
        storm.addAlert(alertFactory.getLessThanThresholdAlert(AlertType.TEMPERATURE, 32.0, "Freezing temperature alert"));
        activeAlerts = storm.addReadings(now, 11.0, 5.1, 31.0, 0.0);
        Assert.assertEquals(1, activeAlerts.size());
        Assert.assertEquals(1, activeAlerts.stream()
                .filter(a->a.getName().equals("Freezing temperature alert")).collect(Collectors.toSet()).size());

        // wind alert
        storm.addAlert(alertFactory.getExceedsThresholdAlert(AlertType.WIND, 30.0, "High winds alert"));
        Assert.assertEquals(1, activeAlerts.size());
        activeAlerts = storm.addReadings(now, 36.0, 5.3, 32.1, 0.0);
        Assert.assertEquals(1, activeAlerts.stream()
                .filter(a->a.getName().equals("High winds alert")).collect(Collectors.toSet()).size());

        // precipitation alert
        storm.addAlert(alertFactory.getExceedsThresholdAlert(AlertType.PRECIPITATION, 2.0, "Flooding alert"));
        activeAlerts = storm.addReadings(now, 36.0, 5.3, 32.1, 2.1 );
        Assert.assertEquals(2, activeAlerts.size());
        Assert.assertEquals(1, activeAlerts.stream()
                .filter(a->a.getName().equals("Flooding alert")).collect(Collectors.toSet()).size());
    }
}
