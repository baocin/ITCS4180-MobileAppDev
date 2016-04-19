package com.github.baocin.inclass11;

////InClass 11
////Group 18
////4-11-16
////Praveenkumar Sangalad
////Michael Pedersen
////Gabriel Lima

import java.io.Serializable;

/**
 * Created by aoi on 4/11/16.
 */
public class Expense implements Serializable{
    String amount;
    String category;
    String date;
    String name;
    String user;
    String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "amount='" + amount + '\'' +
                ", category='" + category + '\'' +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    public Expense(String amount, String category, String date, String name, String user) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.name = name;
        this.user = user;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
