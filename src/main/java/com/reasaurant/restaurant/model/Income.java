package com.reasaurant.restaurant.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "income")
@DynamicUpdate
public class Income {
    @Id
    @Column(name = "id")
    int id;
    @Column(name = "table_id")
    int tableId;
    @Column(name = "table_income")
    double tableIncome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public double getTableIncome() {
        return tableIncome;
    }

    public void setTableIncome(double tableIncome) {
        this.tableIncome = tableIncome;
    }
}
