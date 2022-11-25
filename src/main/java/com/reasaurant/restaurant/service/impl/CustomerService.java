package com.reasaurant.restaurant.service.impl;

import com.reasaurant.restaurant.dao.ICustomerDao;
import com.reasaurant.restaurant.model.Customer;
import com.reasaurant.restaurant.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    ICustomerDao customerDao;

    @Override
    public void newCustomer(int tableId, int count) {
        Customer customer = new Customer();
        customer.setTableId(tableId);
        customer.setCustomerNumber(count);
        customerDao.save(customer);
    }

    @Override
    public Page<Customer> getCustomerList(Pageable pageable) {
        return customerDao.findAll(pageable);
    }

    @Override
    public Page<Customer> getCustomerListByTableId(int id, Pageable pageable) {
        return customerDao.findByTableId(id,pageable);
    }
}
