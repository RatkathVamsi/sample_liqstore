package com.sample.model;

public class TableDisplay {
    private String productName;
    private int previousStock;
    private int quantity;
    private float amount;
    private float avgCost;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPreviousStock() {
        return previousStock;
    }

    public void setPreviousStock(int previousStock) {
        this.previousStock = previousStock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAvgCost() {
        return avgCost;
    }

    public void setAvgCost(float avgCost) {
        this.avgCost = avgCost;
    }

    @Override
    public String toString() {
        return "TableDisplay{" +
                "productName='" + productName + '\'' +
                ", previousStock=" + previousStock +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", avgCost=" + avgCost +
                '}';
    }
}
