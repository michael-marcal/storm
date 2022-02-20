package com.michaelmarcal.commons.storm.composition;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Wind implements Alertable{

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

    public Double getLatestWindSpeed() {
        return windReadings.lastElement().getValue();
    }

    public Double getLatestWindDirection() {
        return windReadings.lastElement().getDirection();
    }

    public Double getMaximumWindSpeed() {
        return Collections.max( this.windReadings, SeriesPoint.compareSeriesPoint ).getValue();
    }

    public Double getMinimumWindSpeed() {
        return Collections.min( this.windReadings, SeriesPoint.compareSeriesPoint ).getValue();
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
}
