package com.michaelmarcal.commons.storm.observation;

import java.util.Collections;
import java.util.Vector;

public interface Observable {

    <T extends SeriesPoint> Vector<T> getObservations();

    default Double getLatestObservationValue() {
        return getObservations().lastElement().getValue();
    }

    default Double getMaximumObservationValue() {
        return Collections.max( getObservations(), SeriesPoint.compareSeriesPoint ).getValue();
    }

    default Double getMinimumObservationValue() {
        return Collections.min( getObservations(), SeriesPoint.compareSeriesPoint ).getValue();
    }
}
