package com.michaelmarcal.commons.storm.data.isd;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class IsdTemperature {
    @Getter @Setter private Double temperatureCelsius;
    @Getter @Setter private String qualityCode;
}
