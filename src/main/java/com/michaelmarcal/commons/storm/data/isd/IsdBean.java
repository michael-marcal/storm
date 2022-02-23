package com.michaelmarcal.commons.storm.data.isd;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;

import java.time.LocalDateTime;

public class IsdBean {

    @CsvBindByName(column = "DATE")
    @CsvDate("yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime time;

    @CsvCustomBindByName(column = "WIND", converter = TextToWind.class)
    private IsdWind wind;

    @CsvCustomBindByName(column = "TMP", converter = TextToTemperature.class)
    private IsdTemperature temperature;

    @CsvCustomBindByName(column = "AA1", converter = TextToPrecipitation.class)
    private IsdPrecipitation precipitation;

}
