package com.sample.model;

public class Sales {
    private String departmentName;
    private String date;
    private int no;
    private float amount;
    private String itemName;
    private float qty;

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

    public float getAmount() {
        return amount;
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
