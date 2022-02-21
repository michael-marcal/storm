package com.michaelmarcal.commons.storm.alert;

import com.michaelmarcal.commons.storm.observation.SeriesPoint;

import java.util.function.Function;

public class AlertFactory {

    public Alert getExceedsThresholdAlert( AlertType alertType, Double threshold, String name ) {
        Function<SeriesPoint, Boolean> f = (sp) -> sp.getValue() > threshold;
        return new Alert( alertType, f, name);
    }

    public Alert getLessThanThresholdAlert( AlertType alertType, Double threshold, String name ) {
        Function<SeriesPoint, Boolean> f = (sp) -> sp.getValue() < threshold;
        return new Alert(alertType, f, name);
    }
}
