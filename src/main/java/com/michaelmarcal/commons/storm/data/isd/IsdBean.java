package com.michaelmarcal.commons.storm.data.isd;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Getter;

import java.time.LocalDateTime;

public class IsdBean {

    @CsvBindByName(column = "DATE")
    @CsvDate("yyyy-MM-dd'T'HH:mm:ss")
    @Getter private LocalDateTime time;

    @CsvBindByName(column = "SOURCE")
    @Getter private String dataSourceFlag;

    @CsvBindByName(column = "NAME")
    @Getter private String locationName;

    @CsvCustomBindByName(column = "WND", converter = TextToWind.class)
    @Getter private IsdWind wind;

    @CsvCustomBindByName(column = "TMP", converter = TextToTemperature.class)
    @Getter private IsdTemperature temperature;

    @CsvCustomBindByName(column = "AA1", converter = TextToPrecipitation.class)
    @Getter private IsdPrecipitation precipitation;
}
