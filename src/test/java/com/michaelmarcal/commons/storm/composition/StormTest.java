package com.michaelmarcal.commons.storm.composition;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Set;

public class StormTest {

    @Test
    public void test_Storm() {
        Storm storm = new Storm();
        LocalDateTime now = LocalDateTime.now();

        storm.addReadings(now, 10.0, 5.0, 33.0, 0.0);
        Set<String> alerts = storm.getAlerts();
        Assert.assertEquals(0, alerts.size());

        storm.addReaddings(11.0, 5.1, 32.0, 0.0);
        alerts = storm.getAlerts();
        Assert.assertEquals(1, alerts.size());
        Assert.assertEquals("Freezing temperature alert",
                alerts.stream()
                        .filter(alert -> "Freezing temperature alert".equals(alert)).findAny().orElse(null));
    }

    @Test
    public void test_Alerts(){
        Storm storm = new Storm();
        LocalDateTime now = LocalDateTime.now();

        // no alerts
        storm.addReadings(now, 10.0, 5.0, 33.0, 0.0);
        Set<String> alerts = storm.getAlerts();
        Assert.assertEquals(0, alerts.size());

        // temperature alert
        storm.addReadings(now, 11.0, 5.1, 32.0, 0.0);
        alerts = storm.getAlerts();
        Assert.assertEquals(1, alerts.size());
        Assert.assertEquals("Freezing temperature alert", alerts.stream()
                .filter(alert -> "Freezing temperature alert".equals(alert)).findAny().orElse(null));

        // wind alert
        storm.addReadings(now, 36.0, 5.3, 32.1, 0.0);
        alerts = storm.getAlerts();
        Assert.assertEquals(2, alerts.size());
        Assert.assertEquals("High winds alert", alerts.stream()
                .filter(alert -> "High winds alert".equals(alert)).findAny().orElse(null));

        storm.addReadings(now, 36.0, 5.3, 32.1, 2.0);
        alerts = storm.getAlerts();
        Assert.assertEquals(3, alerts.size());
        Assert.assertEquals("Flooding alert", alerts.stream()
                .filter(alert -> "Flooding alert".equals(alert)).findAny().orElse(null));
    }
}
