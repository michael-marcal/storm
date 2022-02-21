package com.michaelmarcal.commons.storm.observation;

import java.time.LocalDateTime;
import java.util.Comparator;

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

    public static Comparator<SeriesPoint> compareSeriesPoint = Comparator.comparing(SeriesPoint::getValue);
}
