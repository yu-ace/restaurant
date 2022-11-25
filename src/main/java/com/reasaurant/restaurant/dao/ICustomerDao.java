package com.reasaurant.restaurant.dao;

import com.reasaurant.restaurant.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerDao extends JpaRepository<Customer,Integer> {
    Page<Customer> findByTableId(int id, Pageable pageable);
}
