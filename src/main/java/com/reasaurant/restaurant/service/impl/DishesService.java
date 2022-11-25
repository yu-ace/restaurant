package com.reasaurant.restaurant.service.impl;

import com.reasaurant.restaurant.dao.IDishesDao;
import com.reasaurant.restaurant.model.Dishes;
import com.reasaurant.restaurant.service.IDishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DishesService implements IDishesService {

    @Autowired
    IDishesDao dishesDao;

    @Override
    public void newDishes(String name, double price, String category) {
        Dishes dishes = new Dishes();
        dishes.setName(name);
        dishes.setPrice(price);
        dishes.setCategory(category);
        dishesDao.save(dishes);
    }

    @Override
    public Dishes getDishesById(int id) {
        return dishesDao.findById(id);
    }

    @Override
    public Page<Dishes> getDishesByCategory(String category, Pageable pageable) {
        return dishesDao.findByCategory(category,pageable);
    }

    @Override
    public Page<Dishes> getDishesPage(Pageable pageable) {
        return dishesDao.findAll(pageable);
    }
}
