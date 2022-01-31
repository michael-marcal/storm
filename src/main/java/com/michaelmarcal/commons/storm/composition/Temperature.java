package com.michaelmarcal.commons.storm.composition;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Vector;

public class Temperature {

    private final Vector<SeriesPoint> temperatures;

    public Temperature() {
        this.temperatures = new Vector<>();
    }

    public void addTemperaturePoint(LocalDateTime time, Double temperature ){
        temperatures.add( new SeriesPoint(time, temperature) );
    }

    public Double getLatestTemperature( ) {
        return temperatures.lastElement().getValue();
    }

    public Double getMaximumTemperature( ) {
        return Collections.max(this.temperatures, (o1, o2) -> {
            if( o1.getValue() > o2.getValue() ){
                return 1;
            } else if( o1.getValue() < o2.getValue() ) {
                return -1;
            }
            return 0;
        }).getValue();
    }

    public Double getMinimumTemperature( ) {
        return Collections.min(this.temperatures, (o1, o2) -> {
            if( o1.getValue() > o2.getValue() ){
                return 1;
            } else if( o1.getValue() < o2.getValue() ) {
                return -1;
            }
            return 0;
        }).getValue();
    }
}
