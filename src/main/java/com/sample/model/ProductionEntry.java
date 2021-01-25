package com.sample.model;

import java.util.Arrays;

public class ProductionEntry {
    private String GST;
    private String processingRate;
    private String udCakeQty;
    private String consumptionQties[];

    public String getGST() {
        return GST;
    }

    public void setGST(String GST) {
        this.GST = GST;
    }

    public String getProcessingRate() {
        return processingRate;
    }

    public void setProcessingRate(String processingRate) {
        this.processingRate = processingRate;
    }

    public String getUdCakeQty() {
        return udCakeQty;
    }

    public void setUdCakeQty(String udCakeQty) {
        this.udCakeQty = udCakeQty;
    }

    public String[] getConsumptionQties() {
        return consumptionQties;
    }

    public void setConsumptionQties(String[] consumptionQties) {
        this.consumptionQties = consumptionQties;
    }

    @Override
    public String toString() {
        return "ProductionEntry{" +
                "GST='" + GST + '\'' +
                ", processingRate='" + processingRate + '\'' +
                ", udCakeQty='" + udCakeQty + '\'' +
                ", consumptionQties=" + Arrays.toString(consumptionQties) +
                '}';
    }
}
