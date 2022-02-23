package com.michaelmarcal.commons.storm.data.isd;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class TextToWind<T, I> extends AbstractBeanField<T, I> {

    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        String[] split = s.split(",", 5);
        return new IsdWind(parseWindDirection(split[0]),split[1], split[2], parseWindSpeed(split[3]), split[4]);
    }

    private Integer parseWindDirection(String windDirectionString ) {
        return Integer.parseInt(windDirectionString);
    }

    private Double parseWindSpeed(String windSpeedString ) {
        return Integer.parseInt(windSpeedString) / 10.0;
    }

    @Override
    public String convertToWrite(Object value) {
        IsdWind isdWind = (IsdWind) value;

        return String.format("%d,%s,%s,%04d,%s",
                isdWind.getWindDirection(),
                isdWind.getWindDirectionQualityCode(),
                isdWind.getWindTypeCode(),
                (int)(isdWind.getWindSpeed() * 10.0),
                isdWind.getWindSpeedQualityCode()
        );
    }
}
