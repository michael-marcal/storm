package com.michaelmarcal.commons.storm.composition;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.function.Function;

public class AlertTest {

    @Test
    public void test_Alert() {
        Function<SeriesPoint, Boolean> f = (sp) -> sp.getValue() > 10;
        Alert a = new Alert(AlertType.WIND, f, "High Wind");

        LocalDateTime now = LocalDateTime.now();
        SeriesPoint sp = new SeriesPoint( now, 15);
        Assert.assertTrue(a.evaluateAlert(sp));

        Assert.assertEquals(sp, a.getAlertData() );
        Assert.assertEquals(1, a.getHistoricalAlertData().size());

        SeriesPoint sp2 = new SeriesPoint( now, 8);
        Assert.assertFalse(a.evaluateAlert(sp2));
        Assert.assertNull(a.getAlertData());
        Assert.assertEquals(1, a.getHistoricalAlertData().size());
    }
}
