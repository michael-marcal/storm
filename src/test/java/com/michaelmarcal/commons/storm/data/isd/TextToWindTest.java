package com.michaelmarcal.commons.storm.data.isd;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextToWindTest {

    @Test
    public void test_Convert() throws CsvConstraintViolationException, CsvDataTypeMismatchException {
        TextToWind textToWind = new TextToWind();

        String isdWindString = "210,1,N,0021,1";
        IsdWind isdWind = (IsdWind) textToWind.convert(isdWindString);

        IsdWind expected1 = new IsdWind(210, "1", "N", 2.1, "1");

        assertAll(
                ()->assertEquals(expected1.getWindDirection(), isdWind.getWindDirection()),
                ()->assertEquals(expected1.getWindDirectionQualityCode(), isdWind.getWindSpeedQualityCode()),
                ()->assertEquals(expected1.getWindTypeCode(), isdWind.getWindTypeCode()),
                ()->assertEquals(expected1.getWindSpeed(), isdWind.getWindSpeed(), 0.01),
                ()->assertEquals(expected1.getWindSpeedQualityCode(), isdWind.getWindDirectionQualityCode())
        );
    }

    @Test
    public void test_convertToWrite() {
        TextToWind textToWind = new TextToWind();

        IsdWind isdWind = new IsdWind(210, "1", "N", 2.1, "1");
        String expected1 = "210,1,N,0021,1";

        assertEquals(expected1, textToWind.convertToWrite(isdWind));
    }
}
