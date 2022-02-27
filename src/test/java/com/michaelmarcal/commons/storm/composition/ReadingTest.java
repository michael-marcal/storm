package com.michaelmarcal.commons.storm.composition;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadingTest {

    @Test
    public void test_Reading(){
        LocalDateTime now = LocalDateTime.now();
        Reading reading = new Reading(now, 10.0, 20.0, 15.0, 1.0);

        assertAll(
                // Ensure that all declared fields are tested
                ()->assertEquals(5, reading.getClass().getDeclaredFields().length),
                // Check field values
                ()->assertEquals(now, reading.getTime()),
                ()->assertEquals(10.0, reading.getWindSpeed()),
                ()->assertEquals(20.0, reading.getWindDirection()),
                ()->assertEquals(15.0, reading.getTemperature()),
                ()->assertEquals(1.0, reading.getPrecipitation())
        );
    }
}
