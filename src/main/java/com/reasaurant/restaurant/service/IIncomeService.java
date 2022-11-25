package com.reasaurant.restaurant.service;

import com.reasaurant.restaurant.model.Income;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IIncomeService {
    Page<Income> getIncomeByTableId(int id, Pageable pageable);
    Page<Income> getIncomeList(Pageable pageable);
}
