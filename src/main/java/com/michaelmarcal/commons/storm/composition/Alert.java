package com.michaelmarcal.commons.storm.composition;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
@RequiredArgsConstructor
public class Alert {

    @Getter
    @NonNull private AlertType type;
    @NonNull private Function<SeriesPoint, Boolean> alertTest;
    @NonNull @Getter private String name;
    @Getter
    private SeriesPoint alertData = null;
    @Getter
    private final List<SeriesPoint> historicalAlertData = new ArrayList<>();

    public Boolean evaluateAlert( SeriesPoint sp ) {
        if(alertTest.apply( sp ) ) {
            this.alertData = sp;
            this.historicalAlertData.add(sp);
            return Boolean.TRUE;
        }
        this.alertData = null;
        return Boolean.FALSE;
    }
}
