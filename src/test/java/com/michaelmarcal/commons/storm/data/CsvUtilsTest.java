package com.michaelmarcal.commons.storm.data;

import com.michaelmarcal.commons.storm.TestHelpers;
import com.michaelmarcal.commons.storm.data.isd.IsdBean;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvUtilsTest {

    @Test
    public void test_readIsd() {
        TestHelpers testHelpers = new TestHelpers();
        List<IsdBean> data = new ArrayList<>();

        try{
            data = CsvUtils.readIsd(testHelpers.getResourceFilePath("isdSample.csv"));
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        assertEquals(10, data.size());

        IsdBean isdBean = data.get(1);
        LocalDateTime expectedDate = LocalDateTime.parse("2022-01-01T00:51:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        String expectedSource = "7";
        String expectedLocation = "NEWARK LIBERTY INTERNATIONAL AIRPORT, NJ US";
        double expectedWindSpeed = 1.5;
        double expectedTemperature = 10.6;
        double expectedPrecipitation = 0.0;
        assertAll(
                // Ensure that all declared fields are tested
                ()->assertEquals(6, isdBean.getClass().getDeclaredFields().length),
                // Check field values
                ()->assertEquals(expectedDate, isdBean.getTime()),
                ()->assertEquals(expectedSource, isdBean.getDataSourceFlag()),
                ()->assertEquals(expectedLocation, isdBean.getLocationName()),
                ()->assertEquals(expectedWindSpeed, isdBean.getWind().getWindSpeed(), 0.01),
                ()->assertEquals(expectedTemperature, isdBean.getTemperature().getTemperatureCelsius(), 0.01),
                ()->assertEquals(expectedPrecipitation, isdBean.getPrecipitation().getDepth(), 0.01)
        );
    }
}
