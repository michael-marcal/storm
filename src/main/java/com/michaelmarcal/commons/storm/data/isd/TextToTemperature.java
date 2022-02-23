package com.michaelmarcal.commons.storm.data.isd;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class TextToTemperature<T, I> extends AbstractBeanField<T, I> {

    private Double parseTemp( String tempStr ) {
        boolean isNegative = tempStr.startsWith("-");
        int tempInt = Integer.parseInt(tempStr.substring(1));
        double temp = tempInt / 10.0;
        if(isNegative)
            temp = temp * -1.0;
        return temp;
    }

    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        String[] split = s.split(",", 2);
        return new IsdTemperature( parseTemp(split[0]), split[1] );
    }

    @Override
    public String convertToWrite(Object value) {
        IsdTemperature temperature = (IsdTemperature) value;
        return String.format( "%s,%s", tempToString(temperature), temperature.getQualityCode());
    }

    private String tempToString( IsdTemperature temperature) {
        double tempDouble = temperature.getTemperatureCelsius() * 10;
        int tempInt = (int) tempDouble;
        String sign = "+";
        if(tempInt < 0 ){
            sign = "-";
        }
        return String.format("%s%04d", sign, Math.abs(tempInt));
    }
}
