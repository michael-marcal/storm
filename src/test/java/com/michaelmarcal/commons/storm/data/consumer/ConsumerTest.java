package com.michaelmarcal.commons.storm.data.consumer;

import com.michaelmarcal.commons.storm.TestHelpers;
import com.michaelmarcal.commons.storm.composition.Storm;
import com.michaelmarcal.commons.storm.data.isd.IsdBean;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ConsumerTest {
    private final TestHelpers testHelpers = new TestHelpers();

    @Test
    public void test_Consumer_noProducers() throws InterruptedException{
        // given
        Storm storm = new Storm();
        Processor stormProcessor = new StormProcessor(storm);
        TransferQueue<IsdBean> transferQueue = new LinkedTransferQueue<>();
        ExecutorService exService = Executors.newFixedThreadPool(1);
        Consumer consumer = new Consumer(transferQueue, stormProcessor);

        // when
        exService.execute(consumer);

        // then
        assertFalse(exService.awaitTermination(1000, TimeUnit.MILLISECONDS));
        exService.shutdown();

        assertEquals(0, consumer.getMessagesConsumed().intValue());
        assertEquals(0, consumer.getBacklog());
    }

    @Test
    public void test_Consumer() throws InterruptedException, FileNotFoundException {
        // given
        Storm storm = new Storm();
        Processor stormProcessor = new StormProcessor(storm);
        TransferQueue<IsdBean> transferQueue = new LinkedTransferQueue<>();
        IsdBean bean = testHelpers.getTestIsdBean();
        transferQueue.add(bean);
        ExecutorService exService = Executors.newFixedThreadPool(1);
        Consumer consumer = new Consumer(transferQueue, stormProcessor);

        // when
        exService.execute(consumer);

        // then
        assertFalse(exService.awaitTermination(1000, TimeUnit.MILLISECONDS));
        exService.shutdown();

        assertEquals(1, consumer.getMessagesConsumed().intValue());
        assertEquals(0, consumer.getBacklog());
    }

}
