package com.michaelmarcal.commons.storm.data.consumer;

import com.michaelmarcal.commons.storm.TestHelpers;
import com.michaelmarcal.commons.storm.composition.Storm;
import com.michaelmarcal.commons.storm.data.isd.IsdBean;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

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

    @Test
    public void test_ConsumerProcessorError() throws InterruptedException, FileNotFoundException {
        // given
        Processor stormProcessorMock = mock(StormProcessor.class);
        doThrow(RuntimeException.class).when(stormProcessorMock).process(isA(Object.class));
        TransferQueue<IsdBean> transferQueue = new LinkedTransferQueue<>();
        IsdBean bean = testHelpers.getTestIsdBean();
        transferQueue.add(bean);
        ExecutorService exService = Executors.newFixedThreadPool(1);
        Consumer consumer = new Consumer(transferQueue, stormProcessorMock);

        // when
        exService.execute(consumer);

        // then
        assertFalse(exService.awaitTermination(1000, TimeUnit.MILLISECONDS));
        exService.shutdown();

        assertEquals(0, consumer.getMessagesConsumed().intValue());
        assertEquals(0, consumer.getBacklog());
        assertEquals(1, consumer.getMessageErrors().intValue());
    }

    @Test
    public void test_ConsumerInterruptedException() throws InterruptedException, FileNotFoundException{
        // given
        Storm storm = new Storm();
        Processor stormProcessor = new StormProcessor(storm);
        TransferQueue<IsdBean> transferQueueMock =  mock(LinkedTransferQueue.class);
        when(transferQueueMock.take()).thenThrow(InterruptedException.class);
        IsdBean bean = testHelpers.getTestIsdBean();
        transferQueueMock.add(bean);
        ExecutorService exService = Executors.newFixedThreadPool(1);
        Consumer consumer = new Consumer(transferQueueMock, stormProcessor);

        // when
        exService.execute(consumer);

        // then
        assertFalse(exService.awaitTermination(1000, TimeUnit.MILLISECONDS));
        exService.shutdown();

        assertEquals(0, consumer.getMessagesConsumed().intValue());
        assertEquals(0, consumer.getBacklog());
        assertEquals(0, consumer.getMessageErrors().intValue());
    }

}
