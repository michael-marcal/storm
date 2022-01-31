package com.michaelmarcal.commons.storm.composition;

public class Storm {

    private final Precipitation precipiation;
    private final Temperature temperature;
    private final Wind wind;

    public Storm(){
        this.precipiation = new Precipitation();
        this.temperature = new Temperature();
        this.wind = new Wind();
    }
}
