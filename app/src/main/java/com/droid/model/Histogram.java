package com.droid.model;

public class Histogram {
    int data;
    String label;

    public Histogram(int data, String label) {
        this.data = data;
        this.label = label;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
