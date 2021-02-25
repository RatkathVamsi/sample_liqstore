package com.sample.model;

public class Records {
    private float consumptionQty;
    private String itemName;
    private float stock;

    public float getConsumptionQty() {
        return consumptionQty;
    }

    public void setConsumptionQty(float consumptionQty) {
        this.consumptionQty = consumptionQty;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Records{" +
                "consumptionQty=" + consumptionQty +
                ", itemName='" + itemName + '\'' +
                ", stock=" + stock +
                '}';
    }

    public void setStock(float stock) {
        this.stock = stock;
    }
}
