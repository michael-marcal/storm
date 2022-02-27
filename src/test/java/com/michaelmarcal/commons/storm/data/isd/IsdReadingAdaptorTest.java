package com.michaelmarcal.commons.storm.data.isd;

import com.michaelmarcal.commons.storm.TestHelpers;
import com.michaelmarcal.commons.storm.composition.Reading;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IsdReadingAdaptorTest {
    private final TestHelpers testHelpers = new TestHelpers();

    @Test
    public void test_Adaptor(){
        List<IsdBean> data = new ArrayList<>();

        try{
            data = testHelpers.getTestIsdList();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        IsdBean bean = data.get(1);
        IsdReadingAdaptor adaptor = new IsdReadingAdaptor(bean);
        Reading reading = adaptor.getReading();

        assertAll(
                // Ensure that all declared fields are tested
                ()->assertEquals(5, reading.getClass().getDeclaredFields().length),
                // Check field values
                ()->assertEquals(LocalDateTime.parse("2022-01-01T00:51"), reading.getTime()),
                ()->assertEquals(1.5, reading.getWindSpeed()),
                ()->assertEquals(160.0, reading.getWindDirection()),
                ()->assertEquals(10.6, reading.getTemperature()),
                ()->assertEquals(0.0, reading.getPrecipitation())
        );
    }
}
