package com.michaelmarcal.commons.storm.data.collection;

import com.michaelmarcal.commons.storm.TestHelpers;
import com.michaelmarcal.commons.storm.data.isd.IsdBean;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class CollectorTest {

    @Test
    public void test_fetchData() {
        TestHelpers testHelpers = new TestHelpers();
        IsdBean data;
        Collector collector = new Collector( testHelpers.getResourceFilePath("isdSample.csv"));
        data = collector.getReading();

        LocalDateTime expectedDate = LocalDateTime.parse("2022-01-01T00:51:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        String expectedSource = "7";
        String expectedLocation = "NEWARK LIBERTY INTERNATIONAL AIRPORT, NJ US";
        double expectedWindSpeed = 1.5;
        double expectedTemperature = 10.6;
        double expectedPrecipitation = 0.0;
        assertAll(
                // Ensure that all declared fields are tested
                ()->assertEquals(6, data.getClass().getDeclaredFields().length),
                // Check field values
                ()->assertEquals(expectedDate, data.getTime()),
                ()->assertEquals(expectedSource, data.getDataSourceFlag()),
                ()->assertEquals(expectedLocation, data.getLocationName()),
                ()->assertEquals(expectedWindSpeed, data.getWind().getWindSpeed(), 0.01),
                ()->assertEquals(expectedTemperature, data.getTemperature().getTemperatureCelsius(), 0.01),
                ()->assertEquals(expectedPrecipitation, data.getPrecipitation().getDepth(), 0.01)
        );
    }

    @Test
    public void test_MissingCsv(){
        Collector collector = new Collector( "nonExistentFile.csv");
        IsdBean data = collector.getReading();
        assertNull(data);
    }

    @Test
    public void test_DrainReadings(){
        TestHelpers testHelpers = new TestHelpers();
        IsdBean data;
        Collector collector = new Collector( testHelpers.getResourceFilePath("isdSample.csv"));
        for( int i = 0; i < 7; i++ ){
            data = collector.getReading();
            assertNotNull(data);
        }
        data = collector.getReading();
        assertNull(data);
    }
}
