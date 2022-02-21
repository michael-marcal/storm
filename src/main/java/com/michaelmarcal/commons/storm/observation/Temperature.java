package com.michaelmarcal.commons.storm.observation;

import com.michaelmarcal.commons.storm.alert.Alert;
import com.michaelmarcal.commons.storm.alert.AlertType;
import com.michaelmarcal.commons.storm.alert.Alertable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Temperature implements Alertable, Observable {

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

    @Override
    public Vector<SeriesPoint> getObservations() {
        return this.temperatures;
    }
}
