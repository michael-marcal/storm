package com.michaelmarcal.commons;

import com.michaelmarcal.commons.storm.composition.Storm;

import java.util.Set;

/**
 * App for Storm
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Storm storm = new Storm();
        storm.addReaddings( 10.0, 5.0, 32.0, 0.0);

        Set<String> alerts = storm.getAlerts();
        for ( String alert: alerts) {
            System.out.println(alert);
        }
    }
}
