package com.michaelmarcal.commons.storm.composition;

import com.michaelmarcal.commons.storm.alert.Alert;
import com.michaelmarcal.commons.storm.observation.Precipitation;
import com.michaelmarcal.commons.storm.observation.Temperature;
import com.michaelmarcal.commons.storm.observation.Wind;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class Storm {

    private final Precipitation precipitation;
    private final Temperature temperature;
    private final Wind wind;

    public Storm(){
        this.precipitation = new Precipitation();
        this.temperature = new Temperature();
        this.wind = new Wind();
    }

    public List<Alert> addReadings( Reading reading ){
        return addReadings(
                reading.getTime(),
                reading.getWindSpeed(),
                reading.getWindDirection(),
                reading.getTemperature(),
                reading.getPrecipitation()
        );
    }

    private List<Alert> addReadings(LocalDateTime time, Double windSpeed, Double windDirection,
                                   Double temperature, Double precipitation) {
        List<Alert> windAlerts = this.wind.addWindReading( time, windSpeed, windDirection );
        List<Alert> tempAlerts = this.temperature.addTemperaturePoint( time, temperature );
        List<Alert> precipitationAlerts = this.precipitation.addPrecipitationReading(time, precipitation);
        return Stream.of(windAlerts, tempAlerts, precipitationAlerts).flatMap(Collection::stream).toList();
    }

    public void addAlert( Alert alert ) {
        switch (alert.getType()) {
            case WIND -> wind.addAlert(alert);
            case TEMPERATURE -> temperature.addAlert(alert);
            case PRECIPITATION -> precipitation.addAlert(alert);
            default -> throw new IllegalArgumentException(alert.getType() + " is not a valid AlertType");
        }
    }

    public List<Alert> getAlerts() {
        return Stream.of(
                    getWindAlerts(),
                    getTemperatureAlerts(),
                    getPrecipitationAlerts())
                .flatMap(Collection::stream).toList();
    }

    private List<Alert> getWindAlerts() {
        return wind.getAlerts();
    }

    private List<Alert> getTemperatureAlerts() {
        return temperature.getAlerts();
    }

    private List<Alert> getPrecipitationAlerts() {
        return precipitation.getAlerts();
    }

}
