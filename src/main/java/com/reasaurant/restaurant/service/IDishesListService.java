package com.reasaurant.restaurant.service;

import com.reasaurant.restaurant.model.DishesList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface IDishesListService {
    void newDishesList(int tableId, int dishesId, String dishesName,
                       double dishesPrice, int dishesCount,Date date);
    Page<DishesList> getDishesListByTableId(int id, Pageable pageable);
    DishesList getDishesListById(int id);
    Page<DishesList> getDishesListByDishesStatusAndDishesCategory(String status,String category,Pageable pageable);
    Page<DishesList> getDishesList(Pageable pageable);
    void changeDishesListStatusById(int id,int cookId);
}
