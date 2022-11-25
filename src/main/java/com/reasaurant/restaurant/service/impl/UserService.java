package com.reasaurant.restaurant.service.impl;

import com.reasaurant.restaurant.dao.IUserDao;
import com.reasaurant.restaurant.model.User;
import com.reasaurant.restaurant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;

    @Override
    public void newUser(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.save(user);
    }

    @Override
    public void changePassword(int id, String password) {
        User user = userDao.findById(id);
        user.setPassword(password);
        userDao.save(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = userDao.findById(id);
        user.setIsDelete(1);
        userDao.save(user);
    }

    @Override
    public User getUserById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public Page<User> getUserList(int isDelete, Pageable pageable) {
        return userDao.findByIsDelete(0,pageable);
    }

    @Override
    public Page<User> getUserListByWageGreater(double wage, Pageable pageable) {
        return userDao.findByWageGreaterThanEqual(wage,pageable);
    }

    @Override
    public Page<User> getUserListByWageLess(double wage, Pageable pageable) {
        return userDao.findByWageLessThanEqual(wage,pageable);
    }
}
