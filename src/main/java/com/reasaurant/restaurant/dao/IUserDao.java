package com.reasaurant.restaurant.dao;

import com.reasaurant.restaurant.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao extends JpaRepository<User,Integer> {
    User findById(int id);
    User findByName(String name);
    Page<User> findByIsDelete(int isDelete, Pageable pageable);
    Page<User> findByWageGreaterThanEqual(double wage, Pageable pageable);
    Page<User> findByWageLessThanEqual(double wage, Pageable pageable);
}
