package com.michaelmarcal.commons.storm.composition;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Temperature implements Alertable{

    private final Vector<SeriesPoint> temperatures;
    private final List<Alert> alerts;

    public Temperature() {
        this.temperatures = new Vector<>();
        this.alerts = new ArrayList<>();
    }

    public List<Alert> addTemperaturePoint(LocalDateTime time, Double temperature ){
        SeriesPoint sp = new SeriesPoint(time, temperature);
        temperatures.add( sp );
        return evaluateAlerts( sp );
    }

    public Double getLatestTemperature( ) {
        return temperatures.lastElement().getValue();
    }

    public Double getMaximumTemperature( ) {
        return Collections.max( this.temperatures, SeriesPoint.compareSeriesPoint ).getValue();
    }

    public Double getMinimumTemperature( ) {
        return Collections.min( this.temperatures, SeriesPoint.compareSeriesPoint ).getValue();
    }

    @Override
    public void addAlert( Alert a ) {
        if( a.getType() != AlertType.TEMPERATURE ){
            throw new IllegalArgumentException( "Alert Type must be TEMPERATURE.  Alert Type provided: " +
                    "" + a.getType() );
        }
        alerts.add(a);
    }

    @Override
    public List<Alert> getAlerts() {
        return alerts;
    }
}
