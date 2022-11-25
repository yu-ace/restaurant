package com.reasaurant.restaurant.service;

import com.reasaurant.restaurant.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService {
    void newCustomer(int tableId,int count);
    Page<Customer> getCustomerList(Pageable pageable);
    Page<Customer> getCustomerListByTableId(int id,Pageable pageable);
}
