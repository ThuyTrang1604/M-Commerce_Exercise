package com.example.models;

public class Orders {

    private int id;
    private String Code;
    private String Name;
    private int CustomerId;
    private String OrderDate;
    private String Description;
    private int Status;
    private String StatusDescription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getStatusDescription() {
        return StatusDescription;
    }

    public Orders() {
    }

    public void setStatusDescription(String statusDescription) {
        StatusDescription = statusDescription;
    }
}
