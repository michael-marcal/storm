package com.michaelmarcal.commons.storm.data.isd;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.util.Objects;

public class TextToPrecipitation<T, I> extends AbstractBeanField<T, I> {

    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if(Objects.equals(s, "")) {
            return null;
        }
        String[] split = s.split(",", 4);
        return new IsdPrecipitation(
                Integer.parseInt(split[0]),
                parseDepth(split[1]),
                split[2],
                split[3]
        );
    }

    private Double parseDepth(String depthString) {
        return Integer.parseInt(depthString) / 10.0;
    }

    @Override
    public String convertToWrite(Object value) {
        IsdPrecipitation isdPrecipitation = (IsdPrecipitation) value;

        return String.format( "%02d,%04d,%s,%s",
                isdPrecipitation.getPeriod(),
                (int) isdPrecipitation.getDepth() * 10,
                isdPrecipitation.getConditionCode(),
                isdPrecipitation.getQualityCode()
        );
    }
}
