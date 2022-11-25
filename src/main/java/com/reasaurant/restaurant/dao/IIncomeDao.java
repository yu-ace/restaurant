package com.reasaurant.restaurant.dao;

import com.reasaurant.restaurant.model.Income;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIncomeDao extends JpaRepository<Income,Integer> {
    Page<Income> findByTableId(int id, Pageable pageable);
}
