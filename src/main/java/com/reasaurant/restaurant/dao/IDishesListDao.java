package com.reasaurant.restaurant.dao;

import com.reasaurant.restaurant.model.DishesList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDishesListDao extends JpaRepository<DishesList,Integer> {
    Page<DishesList> findByTableId(int id, Pageable pageable);
    DishesList findById(int id);
    Page<DishesList> findByDishStatusAndDishesCategory(String status,String category,Pageable pageable);
}
