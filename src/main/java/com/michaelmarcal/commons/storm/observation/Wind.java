package com.michaelmarcal.commons.storm.observation;

import com.michaelmarcal.commons.storm.alert.Alert;
import com.michaelmarcal.commons.storm.alert.AlertType;
import com.michaelmarcal.commons.storm.alert.Alertable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Wind implements Alertable, Observable {

    private final Vector<SeriesPointDirectional> windReadings;
    private final List<Alert> alerts;

    public Wind( ) {
        this.windReadings = new Vector<>();
        this.alerts = new ArrayList<>();
    }

    public List<Alert> addWindReading(LocalDateTime time, Double windSpeed, Double windDirection ) {
        SeriesPointDirectional sp = new SeriesPointDirectional(time, windSpeed, windDirection);
        windReadings.add( sp );
        return evaluateAlerts( sp );
    }

    public Double getLatestWindDirection() {
        return windReadings.lastElement().getDirection();
    }

    @Override
    public void addAlert( Alert a ) {
        if( a.getType() != AlertType.WIND ){
            throw new IllegalArgumentException( "Alert Type must be WIND.  Alert Type provided: " +
                    "" + a.getType() );
        }
        alerts.add(a);
    }

    @Override
    public List<Alert> getAlerts() {
        return alerts;
    }

    @Override
    public Vector<SeriesPointDirectional> getObservations() {
        return this.windReadings;
    }
}
