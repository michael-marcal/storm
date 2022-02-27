package com.michaelmarcal.commons.storm.data.isd;

import com.michaelmarcal.commons.storm.composition.Reading;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class IsdReadingAdaptor {

    @NonNull
    private IsdBean isdBean;

    public Reading getReading(){
        return new Reading(
                isdBean.getTime(),
                isdBean.getWind().getWindSpeed(),
                isdBean.getWind().getWindDirection().doubleValue(),
                isdBean.getTemperature().getTemperatureCelsius(),
                isdBean.getPrecipitation().getDepth()
        );
    }
}
