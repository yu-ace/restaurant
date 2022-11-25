package com.reasaurant.restaurant.controller;

import com.reasaurant.restaurant.model.Dishes;
import com.reasaurant.restaurant.model.DishesList;
import com.reasaurant.restaurant.model.User;
import com.reasaurant.restaurant.service.IDishesListService;
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
import java.util.Date;

@Controller
public class DishesListController {

    @Autowired
    IDishesListService dishesListService;

    @Autowired
    IDishesService dishesService;

    @RequestMapping(path = "/dishesListPage",method = RequestMethod.POST)
    public String dishesListPage(
            @RequestParam(name = "number")
            int n,
            @RequestParam(name = "category")
            String category, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(n,10);
        Page<DishesList> dishesList = dishesListService.getDishesList(of);
        model.addAttribute("dishesLists",dishesListService);
        return "dishesList";
    }

    @RequestMapping(path = "/dishesListByTable",method = RequestMethod.POST)
    public String dishesListByTable(
            @RequestParam(name = "id")
            int tableId,
            @RequestParam(name = "number")
            int n,Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(n,10);
        Page<DishesList> dishesList = dishesListService.getDishesListByTableId(tableId,of);
        model.addAttribute("dishesLists",dishesListService);
        return "dishesList";
    }

    @RequestMapping(path = "/dishesListByDishes",method = RequestMethod.POST)
    public String dishesListByDishes(
            @RequestParam(name = "id")
            int dishesId,
            @RequestParam(name = "number")
            int n,Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(n,10);
        Page<DishesList> dishesList = dishesListService.getDishesListByTableId(dishesId,of);
        model.addAttribute("dishesLists",dishesListService);
        return "dishesList";
    }



    @RequestMapping(path = "customerAddDishes",method = RequestMethod.POST)
    public String customerAddDishes(
            @RequestParam(name = "id")
            int id,
            @RequestParam(name = "count")
            int count,Model model,HttpSession session){
        int tableId =(int) session.getAttribute("tableId");
        Dishes dishes = dishesService.getDishesById(id);
        dishesListService.newDishesList(tableId,id,dishes.getName(),dishes.getPrice(),count,new Date());
        model.addAttribute("tip","添加成功");
        return "customerAddDishes";
    }

    @RequestMapping(path = "/dishesPage",method = RequestMethod.GET)
    public String customerAddDishes(
            @RequestParam(name = "number")
            int n,Model model){
        PageRequest of = PageRequest.of(n,10);
        Page<Dishes> dishes = dishesService.getDishesPage(of);
        model.addAttribute("dishesList",dishes);
        return "customerAddDishes";
    }

    @RequestMapping(path = "/dishes",method = RequestMethod.GET)
    public String dishes(
            @RequestParam(name = "category")
            String category,
            @RequestParam(name = "number")
            int n,Model model){
        PageRequest of = PageRequest.of(n,10);
        Page<Dishes> dishes = dishesService.getDishesByCategory(category,of);
        model.addAttribute("dishesList",dishes);
        return "customerAddDishes";
    }

    @RequestMapping(path = "/startCooking",method = RequestMethod.POST)
    public String startCooking(
            @RequestParam(name = "category")
            String category,
            @RequestParam(name = "number")
            int n,
            Model model,HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(n,10);
        Page<DishesList> dishesLists =
                dishesListService.getDishesListByDishesStatusAndDishesCategory("待做",category,of);
        model.addAttribute("dishList",dishesLists);
        return "startCooking";
    }

    @RequestMapping(path = "/finishDishes",method = RequestMethod.POST)
    public String finishDishes(
            @RequestParam(name = "id")
            int id,
            Model model,HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        dishesListService.changeDishesListStatusById(id, user.getId());
        model.addAttribute("tip","该订单已完成，请开始制作下一个");
        return "startCooking";
    }

    @RequestMapping(path = "/rest",method = RequestMethod.POST)
    public String rest(
            Model model,HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "startCooking";
    }
}
