package com.sample.model;

import java.sql.Date;
import java.util.Arrays;

public class ProductionEntry {
    private Float GST;
    private Float processingRate;
    private Float udCakeQty;
    private String consumptionQties[];
    private Date startDate;
    private Date endDate;
    private String[] itemNames;

    public Float getGST() {
        return GST;
    }

    public void setGST(Float GST) {
        this.GST = GST;
    }

    public Float getProcessingRate() {
        return processingRate;
    }

    public void setProcessingRate(Float processingRate) {
        this.processingRate = processingRate;
    }

    public Float getUdCakeQty() {
        return udCakeQty;
    }

    public void setUdCakeQty(Float udCakeQty) {
        this.udCakeQty = udCakeQty;
    }

    public String[] getConsumptionQties() {
        return consumptionQties;
    }

    public void setConsumptionQties(String[] consumptionQties) {
        this.consumptionQties = consumptionQties;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String[] getItemNames() {
        return itemNames;
    }

    public void setItemNames(String[] itemNames) {
        this.itemNames = itemNames;
    }

    public String[] getStock() {
        return stock;
    }

    public void setStock(String[] stock) {
        this.stock = stock;
    }

    public String[] getSalesConsumptionQties() {
        return salesConsumptionQties;
    }

    public void setSalesConsumptionQties(String[] salesConsumptionQties) {
        this.salesConsumptionQties = salesConsumptionQties;
    }

    public String[] getSalesItemNames() {
        return salesItemNames;
    }

    public void setSalesItemNames(String[] salesItemNames) {
        this.salesItemNames = salesItemNames;
    }

    public String[] getSalesStock() {
        return salesStock;
    }

    public void setSalesStock(String[] salesStock) {
        this.salesStock = salesStock;
    }

    @Override
    public String toString() {
        return "ProductionEntry{" +
                "GST=" + GST +
                ", processingRate=" + processingRate +
                ", udCakeQty=" + udCakeQty +
                ", consumptionQties=" + Arrays.toString(consumptionQties) +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", itemNames=" + Arrays.toString(itemNames) +
                ", stock=" + Arrays.toString(stock) +
                ", salesConsumptionQties=" + Arrays.toString(salesConsumptionQties) +
                ", salesItemNames=" + Arrays.toString(salesItemNames) +
                ", salesStock=" + Arrays.toString(salesStock) +
                '}';
    }

    private String[] stock;
    private String[] salesConsumptionQties;
    private String[] salesItemNames;
    private String[] salesStock;
}