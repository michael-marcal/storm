package com.michaelmarcal.commons.storm.composition;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Vector;

public class Precipitation {

    private final Vector<SeriesPoint> precipitations;
    private final Vector<SeriesPoint> precipiationRates;

    public Precipitation( ) {
        this.precipitations = new Vector<>();
        this.precipiationRates = new Vector<>();
    }

    public void addPrecipitationReading(LocalDateTime time, Double precipitation ){
        Double latest = getLatestPrecipitation();
        precipitations.add( new SeriesPoint(time, precipitation) );
        precipiationRates.add( new SeriesPoint(time, precipitation - latest));
    }

    public Double getLatestPrecipitation( ) {
        if(precipitations.size() == 0 )
            return 0.0;
        return precipitations.lastElement().getValue();
    }

    public Double getLatestPrecipitationRate( ) {
        return precipiationRates.lastElement().getValue();
    }

    public Double getMaximumPrecipitationRate( ) {
        return Collections.max(this.precipiationRates, (o1, o2) -> {
            if( o1.getValue() > o2.getValue() ){
                return 1;
            } else if( o1.getValue() < o2.getValue() ) {
                return -1;
            }
            return 0;
        }).getValue();
    }

    public Double getMinimumPrecipitationRate( ) {
        return Collections.min(this.precipiationRates, (o1, o2) -> {
            if( o1.getValue() > o2.getValue() ){
                return 1;
            } else if( o1.getValue() < o2.getValue() ) {
                return -1;
            }
            return 0;
        }).getValue();
    }

    public Double getMaximumPrecipitation( ) {
        return Collections.max(this.precipitations, (o1, o2) -> {
            if( o1.getValue() > o2.getValue() ){
                return 1;
            } else if( o1.getValue() < o2.getValue() ) {
                return -1;
            }
            return 0;
        }).getValue();
    }

    public Double getMinimumPrecipitation( ) {
        return Collections.min(this.precipitations, (o1, o2) -> {
            if( o1.getValue() > o2.getValue() ){
                return 1;
            } else if( o1.getValue() < o2.getValue() ) {
                return -1;
            }
            return 0;
        }).getValue();
    }
}
