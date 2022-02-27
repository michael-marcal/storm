package com.michaelmarcal.commons.storm.data.producer;

import com.michaelmarcal.commons.storm.TestHelpers;
import com.michaelmarcal.commons.storm.data.collection.Collector;
import com.michaelmarcal.commons.storm.data.isd.IsdBean;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProducerTest {

    @Test
    public void test_Producer() throws InterruptedException{
        TestHelpers testHelpers = new TestHelpers();
        // given
        TransferQueue<IsdBean> transferQueue = new LinkedTransferQueue<>();
        ExecutorService exService = Executors.newFixedThreadPool(2);
        Collector collector = new Collector( testHelpers.getResourceFilePath("isdSample.csv"));
        Producer producer = new Producer(transferQueue, collector);

        // when
        exService.execute(producer);

        // then
        // assert executor didn't terminate on its own.
        assertFalse(exService.awaitTermination(2000, TimeUnit.MILLISECONDS));
        exService.shutdown();

        assertEquals(1, producer.getMessagesProduced().intValue());
    }

    @Test
    public void test_MissingReadings() throws InterruptedException{
        // given
        TransferQueue<IsdBean> transferQueue = new LinkedTransferQueue<>();
        ExecutorService exService = Executors.newFixedThreadPool(2);
        Collector collector = new Collector( "nonExistent.csv" );
        Producer producer = new Producer(transferQueue, collector);

        // when
        exService.execute(producer);

        // then
        // assert executor didn't terminate on its own.
        assertFalse(exService.awaitTermination(2000, TimeUnit.MILLISECONDS));
        exService.shutdown();

        assertEquals(0, producer.getMessagesProduced().intValue());
    }
}
