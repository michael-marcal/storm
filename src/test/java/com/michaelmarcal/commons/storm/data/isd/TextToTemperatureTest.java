package com.michaelmarcal.commons.storm.data.isd;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TextToTemperatureTest {

    @Test
    public void test_convert() throws CsvConstraintViolationException, CsvDataTypeMismatchException {
        TextToTemperature textToTemperature = new TextToTemperature();

        String isdTempString = "+0100,1";
        IsdTemperature isdTemperaturePos = (IsdTemperature) textToTemperature.convert(isdTempString);

        IsdTemperature expected1 = new IsdTemperature(10.0, "1");

        assertAll(
                ()->assertEquals(expected1.getTemperatureCelsius(), isdTemperaturePos.getTemperatureCelsius(), 0.01),
                ()->assertEquals(expected1.getQualityCode(), isdTemperaturePos.getQualityCode())
        );

        isdTempString = "-1234,5";
        IsdTemperature isdTemperatureNeg = (IsdTemperature) textToTemperature.convert(isdTempString);
        IsdTemperature expected2 = new IsdTemperature(-123.4, "5");

        assertAll(
                ()->assertEquals(expected2.getTemperatureCelsius(), isdTemperatureNeg.getTemperatureCelsius(), 0.01),
                ()->assertEquals(expected2.getQualityCode(), isdTemperatureNeg.getQualityCode())
        );

        isdTempString = "+0094,5";
        IsdTemperature isdTemperature3 = (IsdTemperature) textToTemperature.convert(isdTempString);
        IsdTemperature expected3 = new IsdTemperature(9.4, "5");

        assertAll(
                ()->assertEquals(expected3.getTemperatureCelsius(), isdTemperature3.getTemperatureCelsius(), 0.01),
                ()->assertEquals(expected3.getQualityCode(), isdTemperature3.getQualityCode())
        );
    }

    @Test
    public void test_convertToWrite() {
        TextToTemperature textToTemperature = new TextToTemperature();
        IsdTemperature isdTemperaturePos = new IsdTemperature(10.0, "1");
        String expected1 = "+0100,1";

        assertEquals(expected1, textToTemperature.convertToWrite(isdTemperaturePos));

        IsdTemperature isdTemperatureNeg = new IsdTemperature(-2.9, "3");
        String expected2 = "-0029,3";

        assertEquals(expected2, textToTemperature.convertToWrite(isdTemperatureNeg));
    }
}
