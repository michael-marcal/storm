package com.michaelmarcal.commons.storm.readings;

import lombok.Getter;

import java.time.LocalDateTime;

public class SeriesPointDirectional extends SeriesPoint{

    @Getter
    private final double direction;

    public SeriesPointDirectional(LocalDateTime timepoint, double value, double direction ) {
        super(timepoint, value);
        this.direction = direction;
    }
}
