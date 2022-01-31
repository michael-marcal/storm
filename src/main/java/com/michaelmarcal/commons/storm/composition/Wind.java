package com.michaelmarcal.commons.storm.composition;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Vector;

public class Wind {

    private final Vector<SeriesPointDirectional> windReadings;

    public Wind( ) {
        this.windReadings = new Vector<>();
    }

    public void addWindReading(LocalDateTime time, Double windSpeed, Double windDirection ) {
        windReadings.add( new SeriesPointDirectional(time, windSpeed, windDirection) );
    }

    public Double getLatestWindSpeed() {
        return windReadings.lastElement().getValue();
    }

    public Double getLatestWindDirection() {
        return windReadings.lastElement().getDirection();
    }

    public Double getMaximumWindSpeed() {
        return Collections.max(this.windReadings, (o1, o2) -> {
            if( o1.getValue() > o2.getValue() ){
                return 1;
            } else if( o1.getValue() < o2.getValue() ) {
                return -1;
            }
            return 0;
        }).getValue();
    }

    public Double getMinimumWindSpeed() {
        return Collections.min(this.windReadings, (o1, o2) -> {
            if( o1.getValue() > o2.getValue() ){
                return 1;
            } else if( o1.getValue() < o2.getValue() ) {
                return -1;
            }
            return 0;
        }).getValue();
    }
}
