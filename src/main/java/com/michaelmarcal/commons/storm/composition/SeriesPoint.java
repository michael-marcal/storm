package com.michaelmarcal.commons.storm.composition;

import java.time.LocalDateTime;

import lombok.Getter;

public class SeriesPoint {

    @Getter
    private final double value;
    @Getter
    private final LocalDateTime timepoint;

    public SeriesPoint( LocalDateTime timepoint, double value ) {
        this.value = value;
        this.timepoint = timepoint;
    }
}
