package com.michaelmarcal.commons.storm.composition;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Storm {

    private final Precipitation precipiation;
    private final Temperature temperature;
    private final Wind wind;

    public Storm(){
        this.precipiation = new Precipitation();
        this.temperature = new Temperature();
        this.wind = new Wind();
    }

    public void addReadings( LocalDateTime time, Double windSpeed, Double windDirection,
                             Double temperature, Double precipitation) {
        wind.addWindReading( time, windSpeed, windDirection );
        this.temperature.addTemperaturePoint( time, temperature );
        this.precipiation.addPrecipitationReading(time, precipitation);
    }

    public void addReaddings( Double windSpeed, Double windDirection,
                              Double temperature, Double precipitation) {
        addReadings( LocalDateTime.now(), windSpeed, windDirection, temperature, precipitation);
    }

    public Set<String> getAlerts() {
        Set<String> alerts = new HashSet<>();
        alerts.addAll(getWindAlerts());
        alerts.addAll(getTemperatureAlerts());
        alerts.addAll(getPrecipitationAlerts());
        return alerts;
    }

    private Set<String> getWindAlerts() {
        Set<String> windAlerts = new HashSet<>();
        if( this.wind.getMaximumWindSpeed() > 35.0 ) {
            windAlerts.add("High winds alert");
        }
        return windAlerts;
    }

    private Set<String> getTemperatureAlerts() {
        Set<String> tempAlerts = new HashSet<>();
        if( this.temperature.getLatestTemperature() < 33.0 ) {
            tempAlerts.add( "Freezing temperature alert");
        }
        if( this.temperature.getLatestTemperature() > 90.0 ) {
            tempAlerts.add( "Extreme heat alerat" );
        }
        return tempAlerts;
    }

    private Set<String> getPrecipitationAlerts() {
        Set<String> precipAlerts = new HashSet<>();
        if( this.precipiation.getMaximumPrecipitationRate() > 1.0 ) {
            precipAlerts.add( "Flooding alert" );
        }
        return precipAlerts;
    }

}
