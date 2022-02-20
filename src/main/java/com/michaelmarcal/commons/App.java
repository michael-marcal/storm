package com.michaelmarcal.commons;

import com.michaelmarcal.commons.storm.composition.Alert;
import com.michaelmarcal.commons.storm.composition.Storm;

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
        storm.addReadings( 10.0, 5.0, 32.0, 0.0);

        List<Alert> alerts = storm.getAlerts();
        for ( Alert alert: alerts) {
            System.out.println(alert);
        }
    }
}
