package com.michaelmarcal.commons.storm.data.producer;

import com.michaelmarcal.commons.storm.data.collection.Collector;
import com.michaelmarcal.commons.storm.data.isd.IsdBean;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class Producer implements Runnable{

    @NonNull
    private TransferQueue<IsdBean> transferQueue;
    @NonNull
    private Collector collector;

    @Getter
    private final AtomicInteger messagesProduced = new AtomicInteger();

    @Override
    public void run() {
        IsdBean data = collector.getReading();
        if(data != null){
            transferQueue.add(data);
            messagesProduced.incrementAndGet();
        }
    }
}
