package com.example.lab2;

public class Contact {
    private int id;
    private String name, phone;
    private boolean status;

    private String _URI;

    public Contact(int id, String name, String phone, boolean status, String uri) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this._URI = uri;
    }

    public String getURI() {
        return this._URI;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
