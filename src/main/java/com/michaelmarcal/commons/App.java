package com.michaelmarcal.commons;

import com.michaelmarcal.commons.storm.alert.Alert;
import com.michaelmarcal.commons.storm.composition.Reading;
import com.michaelmarcal.commons.storm.composition.Storm;

import java.time.LocalDateTime;
import java.util.List;

/**
 * App for Storm
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Storm storm = new Storm();
        List<Alert> alerts = storm.addReadings(
                new Reading(LocalDateTime.now(), 10.0, 5.0, 32.0, 0.0)
        );

        for ( Alert alert: alerts) {
            System.out.println(alert);
        }
    }
}
