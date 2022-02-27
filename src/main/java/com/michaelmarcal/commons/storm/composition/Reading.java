package com.michaelmarcal.commons.storm.composition;


import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@AllArgsConstructor
public class Reading {
    @Getter
    private LocalDateTime time;
    @Getter
    private Double windSpeed;
    @Getter
    private Double windDirection;
    @Getter
    private Double temperature;
    @Getter
    private Double precipitation;
}
