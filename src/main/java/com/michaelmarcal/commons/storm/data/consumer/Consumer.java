package com.michaelmarcal.commons.storm.data.consumer;

import com.michaelmarcal.commons.storm.data.isd.IsdBean;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class Consumer implements Runnable{

    @NonNull
    private TransferQueue<IsdBean> transferQueue;
    @NonNull
    private Processor processor;

    @Getter
    private final AtomicInteger messagesConsumed = new AtomicInteger();

    @Override
    public void run() {
        try {
            while(true) {
                IsdBean element = transferQueue.take();
                processMessage(element);
                messagesConsumed.incrementAndGet();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processMessage( IsdBean element ) {
        this.processor.process(element);
    }

    public int getBacklog(){
        return transferQueue.size();
    }
}
