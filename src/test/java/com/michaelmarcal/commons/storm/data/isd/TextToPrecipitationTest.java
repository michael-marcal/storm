package com.michaelmarcal.commons.storm.data.isd;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TextToPrecipitationTest {

    @Test
    public void test_convert() throws CsvConstraintViolationException, CsvDataTypeMismatchException {
        TextToPrecipitation textToPrecipitation = new TextToPrecipitation();

        String isdPrecipitationString = "01,0000,2,5";
        IsdPrecipitation isdPrecipitationZero = (IsdPrecipitation) textToPrecipitation.convert(isdPrecipitationString);

        IsdPrecipitation expected1 = new IsdPrecipitation(1, 0.0, "2", "5");

        assertAll(
                ()->assertEquals(expected1.getPeriod(), isdPrecipitationZero.getPeriod()),
                ()->assertEquals(expected1.getDepth(), isdPrecipitationZero.getDepth(), 0.01),
                ()->assertEquals(expected1.getConditionCode(), isdPrecipitationZero.getConditionCode()),
                ()->assertEquals(expected1.getQualityCode(), isdPrecipitationZero.getQualityCode())
        );

        isdPrecipitationString = "02,0010,3,6";
        IsdPrecipitation isdPrecipitationPos = (IsdPrecipitation) textToPrecipitation.convert(isdPrecipitationString);

        IsdPrecipitation expected2 = new IsdPrecipitation(2, 1.0, "3", "6");

        assertAll(
                ()->assertEquals(expected2.getPeriod(), isdPrecipitationPos.getPeriod()),
                ()->assertEquals(expected2.getDepth(), isdPrecipitationPos.getDepth(), 0.01),
                ()->assertEquals(expected2.getConditionCode(), isdPrecipitationPos.getConditionCode()),
                ()->assertEquals(expected2.getQualityCode(), isdPrecipitationPos.getQualityCode())
        );
    }

    @Test
    public void test_convertNull() throws CsvConstraintViolationException, CsvDataTypeMismatchException {
        TextToPrecipitation textToPrecipitation = new TextToPrecipitation();

        String isdPrecipitationString = "";
        assertEquals(null, textToPrecipitation.convert(isdPrecipitationString));
    }

    @Test
    public void test_convertToWrite() {
        TextToPrecipitation textToPrecipitation = new TextToPrecipitation();
        IsdPrecipitation isdPrecipitationZero = new IsdPrecipitation(1, 0.0, "2", "5");
        String expected1 = "01,0000,2,5";

        assertEquals(expected1, textToPrecipitation.convertToWrite(isdPrecipitationZero));

        IsdPrecipitation isdPrecipitationPos = new IsdPrecipitation(2, 1.0, "3", "6");
        String expected2 = "02,0010,3,6";

        assertEquals(expected2, textToPrecipitation.convertToWrite(isdPrecipitationPos));
    }
}
