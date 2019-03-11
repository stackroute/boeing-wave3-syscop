package com.stackroute.monitorservice.model;

public class HistoricalDockerMetric {

    private String time;
    private String cpu;
    private String mem;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMem() {
        return mem;
    }

    public void setMem(String mem) {
        this.mem = mem;
    }

    public HistoricalDockerMetric(String time, String cpu, String mem) {
        this.time = time;
        this.cpu = cpu;
        this.mem = mem;
    }

    public HistoricalDockerMetric() {
    }

    @Override
    public String toString() {
        return "HistoricalDockerMetric{" +
                "time='" + time + '\'' +
                ", cpu='" + cpu + '\'' +
                ", mem='" + mem + '\'' +
                '}';
    }
}
