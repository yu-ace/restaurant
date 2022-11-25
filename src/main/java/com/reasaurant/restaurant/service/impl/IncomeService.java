package com.reasaurant.restaurant.service.impl;

import com.reasaurant.restaurant.dao.IIncomeDao;
import com.reasaurant.restaurant.model.Income;
import com.reasaurant.restaurant.service.IIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IncomeService implements IIncomeService {

    @Autowired
    IIncomeDao incomeDao;

    @Override
    public Page<Income> getIncomeByTableId(int id, Pageable pageable) {
        return incomeDao.findByTableId(id,pageable);
    }

    @Override
    public Page<Income> getIncomeList(Pageable pageable) {
        return incomeDao.findAll(pageable);
    }
}
