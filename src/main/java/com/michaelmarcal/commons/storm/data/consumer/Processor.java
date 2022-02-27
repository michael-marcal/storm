package com.michaelmarcal.commons.storm.data.consumer;

public interface Processor {

    void process(Object element);
}
