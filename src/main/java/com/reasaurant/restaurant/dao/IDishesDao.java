package com.reasaurant.restaurant.dao;

import com.reasaurant.restaurant.model.Dishes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDishesDao extends JpaRepository<Dishes,Integer> {
    Page<Dishes> findByCategory(String category, Pageable pageable);
    Dishes findById(int id);
}
