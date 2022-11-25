package com.reasaurant.restaurant.service;

import com.reasaurant.restaurant.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    void newUser(String name,String password);
    void changePassword(int id,String password);
    void deleteUser(int id);
    User getUserById(int id);
    User getUserByName(String name);
    Page<User> getUserList(int isDelete,Pageable pageable);
    Page<User> getUserListByWageGreater(double wage,Pageable pageable);
    Page<User> getUserListByWageLess(double wage,Pageable pageable);
}
