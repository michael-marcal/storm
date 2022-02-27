package com.michaelmarcal.commons.storm.data.collection;

import com.michaelmarcal.commons.storm.data.isd.IsdBean;
import com.michaelmarcal.commons.storm.data.isd.IsdUtils;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

public class Collector {

    private final String csvPath;
    private List<IsdBean> readings;

    public Collector(String csvPath){
        this.csvPath = csvPath;
        fetchReadings();
    }

    public IsdBean getReading() {
        if(!readings.isEmpty()) {
            return readings.remove(0);
        }
        return null;
    }

    private void fetchReadings(){
        try {
            this.readings = IsdUtils.getIsdData(csvPath);
        } catch (FileNotFoundException e ) {
            System.out.println(e.getMessage());
            this.readings = new LinkedList<>();
        }
    }
}
