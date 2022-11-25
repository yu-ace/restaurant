package com.reasaurant.restaurant.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "dishes_list")
@DynamicUpdate
public class DishesList {
    @Id
    @Column(name = "dishes_list_id")
    int id;
    @Column(name = "customer_id")
    int customerId;
    @Column(name = "dishes_id")
    int dishId;
    @Column(name = "dishes_name")
    String dishName;
    @Column(name = "dishes_price")
    double dishPrice;
    @Column(name = "dishes_count")
    int dishCount;
    @Column(name = "dishes_status")
    String dishStatus;
    @Column(name = "date")
    Date date;
    @Column(name = "dishes_cook_id")
    int dishesCookId;
    @Column(name = "dishes_category")
    String dishesCategory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(double dishPrice) {
        this.dishPrice = dishPrice;
    }

    public int getDishCount() {
        return dishCount;
    }

    public void setDishCount(int dishCount) {
        this.dishCount = dishCount;
    }

    public String getDishStatus() {
        return dishStatus;
    }

    public void setDishStatus(String dishStatus) {
        this.dishStatus = dishStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDishesCookId() {
        return dishesCookId;
    }

    public void setDishesCookId(int dishesCookId) {
        this.dishesCookId = dishesCookId;
    }

    public String getDishesCategory() {
        return dishesCategory;
    }

    public void setDishesCategory(String dishesCategory) {
        this.dishesCategory = dishesCategory;
    }
}
