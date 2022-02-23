package com.michaelmarcal.commons.storm.data.isd;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class IsdWind {

    @Getter @Setter private Integer windDirection;
    @Getter @Setter private String windDirectionQualityCode;
    @Getter @Setter private String windTypeCode;
    @Getter @Setter private Double windSpeed;
    @Getter @Setter private String windSpeedQualityCode;
}
