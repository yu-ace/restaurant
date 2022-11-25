package com.reasaurant.restaurant.service.impl;

import com.reasaurant.restaurant.dao.IDishesListDao;
import com.reasaurant.restaurant.model.DishesList;
import com.reasaurant.restaurant.service.IDishesListService;
import com.reasaurant.restaurant.service.IDishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DishesListService implements IDishesListService {

    @Autowired
    IDishesListDao dishesListDao;


    @Override
    public void newDishesList(int customerId, int dishesId, String dishesName,
                              double dishesPrice, int dishesCount,Date date) {
        DishesList dishesList = new DishesList();
        dishesList.setCustomerId(customerId);
        dishesList.setDishId(dishesId);
        dishesList.setDishName(dishesName);
        dishesList.setDishPrice(dishesPrice);
        dishesList.setDishCount(dishesCount);
        dishesList.setDishStatus("待做");
        dishesList.setDate(new Date());
        dishesListDao.save(dishesList);
    }

    @Override
    public Page<DishesList> getDishesListByTableId(int id, Pageable pageable) {
        return dishesListDao.findByTableId(id,pageable);
    }

    @Override
    public DishesList getDishesListById(int id){
        return dishesListDao.findById(id);
    }

    @Override
    public Page<DishesList> getDishesListByDishesStatusAndDishesCategory(String status,
                                                                         String category,Pageable pageable){
        return dishesListDao.findByDishStatusAndDishesCategory(status,category,pageable);
    }

    @Override
    public Page<DishesList> getDishesList(Pageable pageable) {
        return dishesListDao.findAll(pageable);
    }

    @Override
    public void changeDishesListStatusById(int id,int cookId) {
        DishesList dishesList = dishesListDao.findById(id);
        dishesList.setDishStatus("已完成");
        dishesList.setDishesCookId(cookId);
        dishesListDao.save(dishesList);
    }
}
