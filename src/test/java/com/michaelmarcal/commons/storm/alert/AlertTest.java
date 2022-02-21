package com.michaelmarcal.commons.storm.alert;

import com.michaelmarcal.commons.storm.observation.SeriesPoint;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AlertTest {

    @Test
    public void test_Alert() {
        Function<SeriesPoint, Boolean> f = (sp) -> sp.getValue() > 10;
        Alert a = new Alert(AlertType.WIND, f, "High Wind");

        LocalDateTime now = LocalDateTime.now();
        SeriesPoint sp = new SeriesPoint( now, 15);

        assertAll(
                ()->assertTrue(a.evaluateAlert(sp)),
                ()->assertEquals(sp, a.getAlertData() ),
                ()->assertEquals(1, a.getHistoricalAlertData().size())
        );

        SeriesPoint sp2 = new SeriesPoint( now, 8);
        assertAll(
                ()->assertFalse(a.evaluateAlert(sp2)),
                ()->assertNull(a.getAlertData()),
                ()->assertEquals(1, a.getHistoricalAlertData().size())
        );
    }
}
