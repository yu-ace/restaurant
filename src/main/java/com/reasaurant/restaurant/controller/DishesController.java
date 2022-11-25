package com.reasaurant.restaurant.controller;

import com.reasaurant.restaurant.model.DishesList;
import com.reasaurant.restaurant.model.User;
import com.reasaurant.restaurant.service.IDishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class DishesController {

    @Autowired
    IDishesService dishesService;

    @RequestMapping(path = "/newDishes",method = RequestMethod.POST)
    public String newDishes(
            @RequestParam(name = "name")
            String name,
            @RequestParam(name = "price")
            double price,
            @RequestParam(name = "category")
            String category, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        dishesService.newDishes(name,price,category);
        model.addAttribute("tip","添加成功");
        return "newDishes";
    }



}
