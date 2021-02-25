package com.sample.model;

import java.text.DecimalFormat;

public class Sales {
    private String departmentName;
    private String date;
    private int no;
    private float amount;
    private String itemName;
    private float qty;
    DecimalFormat df = new DecimalFormat("#.####");
    @Override
    public String toString() {
        return "Sales{" +
                "departmentName='" + departmentName + '\'' +
                ", date='" + date + '\'' +
                ", no=" + no +
                ", amount=" + amount +
                ", itemName='" + itemName + '\'' +
                ", qty=" + qty +
                '}';
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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
