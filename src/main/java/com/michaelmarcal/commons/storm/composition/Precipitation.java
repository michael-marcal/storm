package com.michaelmarcal.commons.storm.composition;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Precipitation implements Alertable{

    private final Vector<SeriesPoint> precipitations;
    private final Vector<SeriesPoint> precipitationRates;
    private final List<Alert> alerts;

    public Precipitation( ) {
        this.precipitations = new Vector<>();
        this.precipitationRates = new Vector<>();
        this.alerts = new ArrayList<>();
    }

    public List<Alert> addPrecipitationReading(LocalDateTime time, Double precipitation ){
        Double latest = getLatestPrecipitation();
        SeriesPoint sp = new SeriesPoint(time, precipitation);
        precipitations.add( sp );
        precipitationRates.add( new SeriesPoint(time, precipitation - latest));
        return evaluateAlerts(sp);
    }

    public Double getLatestPrecipitation( ) {
        if(precipitations.size() == 0 )
            return 0.0;
        return precipitations.lastElement().getValue();
    }

    public Double getLatestPrecipitationRate( ) {
        return precipitationRates.lastElement().getValue();
    }

    public Double getMaximumPrecipitationRate( ) {
        return Collections.max( this.precipitationRates, SeriesPoint.compareSeriesPoint ).getValue();
    }

    public Double getMinimumPrecipitationRate( ) {
        return Collections.min( this.precipitationRates, SeriesPoint.compareSeriesPoint ).getValue();
    }

    public Double getMaximumPrecipitation( ) {
        return Collections.max( this.precipitations, SeriesPoint.compareSeriesPoint ).getValue();
    }

    public Double getMinimumPrecipitation( ) {
        return Collections.min( this.precipitations, SeriesPoint.compareSeriesPoint ).getValue();
    }

    @Override
    public void addAlert( Alert a ) {
        if( a.getType() != AlertType.PRECIPITATION ){
            throw new IllegalArgumentException( "Alert Type must be PRECIPITATION.  Alert Type provided: " +
                    "" + a.getType() );
        }
        alerts.add(a);
    }

    @Override
    public List<Alert> getAlerts() {
        return alerts;
    }

}
