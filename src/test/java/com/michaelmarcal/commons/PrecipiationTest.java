package com.michaelmarcal.commons;

import com.michaelmarcal.commons.storm.composition.Precipitation;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class PrecipiationTest {

    @Test
    public void testPrecipiation( ) {

        Precipitation precip = new Precipitation();
        LocalDateTime now = LocalDateTime.now();

        precip.addPrecipitationReading( now, 0.0);
        Assert.assertEquals(Double.valueOf(0.0), precip.getLatestPrecipitation(), .001);
        Assert.assertEquals(Double.valueOf(0.0), precip.getMaximumPrecipitation(), .001);
        Assert.assertEquals(Double.valueOf(0.0), precip.getMinimumPrecipitation(), .001);
        Assert.assertEquals(Double.valueOf(0.0), precip.getLatestPrecipitationRate(), .001);
        Assert.assertEquals(Double.valueOf(0.0), precip.getMaximumPrecipitationRate(), .001);
        Assert.assertEquals(Double.valueOf(0.0), precip.getMinimumPrecipitationRate(), .001);

        precip.addPrecipitationReading( now.plusMinutes(15L), 0.2);
        Assert.assertEquals(Double.valueOf(0.2), precip.getLatestPrecipitation(), .001);
        Assert.assertEquals(Double.valueOf(0.2), precip.getMaximumPrecipitation(), .001);
        Assert.assertEquals(Double.valueOf(0.0), precip.getMinimumPrecipitation(), .001);
        Assert.assertEquals(Double.valueOf(0.2), precip.getLatestPrecipitationRate(), .001);
        Assert.assertEquals(Double.valueOf(0.2), precip.getMaximumPrecipitationRate(), .001);
        Assert.assertEquals(Double.valueOf(0.0), precip.getMinimumPrecipitationRate(), .001);

        precip.addPrecipitationReading( now.plusMinutes(15L), 0.3);
        Assert.assertEquals(Double.valueOf(0.3), precip.getLatestPrecipitation(), .001);
        Assert.assertEquals(Double.valueOf(0.3), precip.getMaximumPrecipitation(), .001);
        Assert.assertEquals(Double.valueOf(0.0), precip.getMinimumPrecipitation(), .001);
        Assert.assertEquals(Double.valueOf(0.1), precip.getLatestPrecipitationRate(), .001);
        Assert.assertEquals(Double.valueOf(0.2), precip.getMaximumPrecipitationRate(), .001);
        Assert.assertEquals(Double.valueOf(0.0), precip.getMinimumPrecipitationRate(), .001);
    }
}
