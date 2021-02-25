package com.sample.model;

import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;

public class Purchases {
    private String departmentName;
    private Date date;
    private float amount;
    private String itemName;
    private float qty;

    DecimalFormat df = new DecimalFormat("#.####");

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "purchases{" +
                "departmentName='" + departmentName + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", itemName='" + itemName + '\'' +
                ", qty=" + qty +
                '}';
    }

    public String getAmount() {
        return df.format(amount);
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }
}
