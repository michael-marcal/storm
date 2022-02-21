package com.michaelmarcal.commons.storm.alert;

import com.michaelmarcal.commons.storm.observation.SeriesPoint;

import java.util.List;

public interface Alertable {
    List<Alert> getAlerts();
    void addAlert( Alert a );

    default List<Alert> getActiveAlerts() {
        return getAlerts().stream().filter(a -> a.getAlertData() != null).toList();
    }

    default List<SeriesPoint> getHistoricalAlerts() {
        List<Alert> alerts = getAlerts();
        return alerts.stream().flatMap(a->a.getHistoricalAlertData().stream()).toList();
    }

    default List<Alert> evaluateAlerts( SeriesPoint sp ) {
        return getAlerts().stream().filter(a -> a.evaluateAlert(sp)).toList();
    }
}
