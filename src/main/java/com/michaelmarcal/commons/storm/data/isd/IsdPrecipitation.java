package com.michaelmarcal.commons.storm.data.isd;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class IsdPrecipitation {

    @Getter @Setter private int period;
    @Getter @Setter private double depth;
    @Getter @Setter private String conditionCode;
    @Getter @Setter private String qualityCode;
}
