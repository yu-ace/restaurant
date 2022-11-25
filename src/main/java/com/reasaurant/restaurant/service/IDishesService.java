package com.reasaurant.restaurant.service;

import com.reasaurant.restaurant.model.Dishes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDishesService {
    void newDishes(String name,double price,String category);
    Dishes getDishesById(int id);
    Page<Dishes> getDishesByCategory(String category, Pageable pageable);
    Page<Dishes> getDishesPage(Pageable pageable);
}
