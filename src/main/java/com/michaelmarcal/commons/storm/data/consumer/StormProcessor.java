package com.michaelmarcal.commons.storm.data.consumer;

import com.michaelmarcal.commons.storm.composition.Storm;
import com.michaelmarcal.commons.storm.data.isd.IsdBean;
import com.michaelmarcal.commons.storm.data.isd.IsdReadingAdaptor;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class StormProcessor implements Processor{

    @NonNull
    Storm storm;

    @Override
    public void process(Object element) {
        IsdReadingAdaptor adaptor = new IsdReadingAdaptor( (IsdBean) element );
        storm.addReadings(adaptor.getReading());
    }
}
