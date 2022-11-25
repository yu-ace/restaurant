package com.reasaurant.restaurant.controller;

import com.reasaurant.restaurant.model.Dishes;
import com.reasaurant.restaurant.model.DishesList;
import com.reasaurant.restaurant.model.Income;
import com.reasaurant.restaurant.model.User;
import com.reasaurant.restaurant.service.IDishesListService;
import com.reasaurant.restaurant.service.IDishesService;
import com.reasaurant.restaurant.service.IIncomeService;
import com.reasaurant.restaurant.service.IUserService;
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
public class PageController {

    @Autowired
    IDishesListService dishesListService;
    @Autowired
    IIncomeService incomeService;
    @Autowired
    IUserService userService;
    @Autowired
    IDishesService dishesService;


    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String index(){
        return "login";
    }

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String index1(){
        return "login";
    }

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(path = "/userBoard",method = RequestMethod.GET)
    public String userBoard(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "userBoard";
    }

    @RequestMapping(path = "/cookBoard",method = RequestMethod.GET)
    public String cookBoard(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "cookBoard";
    }

    @RequestMapping(path = "/newDishes",method = RequestMethod.GET)
    public String newDishes(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "newDishes";
    }

    @RequestMapping(path = "/dishesList",method = RequestMethod.GET)
    public String dishesList(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<DishesList> dishesList = dishesListService.getDishesList(of);
        model.addAttribute("dishesLists",dishesList);
        return "dishesList";
    }

    @RequestMapping(path = "/incomeList",method = RequestMethod.GET)
    public String incomeList(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<Income> incomeList = incomeService.getIncomeList(of);
        model.addAttribute("incomeLists",incomeList);
        return "incomeList";
    }

    @RequestMapping(path = "/newUser",method = RequestMethod.GET)
    public String newUser(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "newUser";
    }

    @RequestMapping(path = "/userList",method = RequestMethod.GET)
    public String userList(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<User> userList = userService.getUserList(0,of);
        model.addAttribute("userList",userList);
        return "userList";
    }

    @RequestMapping(path = "/deleteUser",method = RequestMethod.GET)
    public String deleteUser(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "deleteUser";
    }

    @RequestMapping(path = "/changePassword",method = RequestMethod.GET)
    public String changePassword(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "changePassword";
    }

    @RequestMapping(path = "/customer",method = RequestMethod.GET)
    public String customer(){
        return "customerFirst";
    }

    @RequestMapping(path = "/customerAddDishes",method = RequestMethod.GET)
    public String customerAddDishes(Model model){
        PageRequest of = PageRequest.of(0,10);
        Page<Dishes> dishes = dishesService.getDishesPage(of);
        model.addAttribute("dishesList",dishes);
        return "customerAddDishes";
    }

    @RequestMapping(path = "/startCooking",method = RequestMethod.GET)
    public String startCooking(Model model,HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登录");
            return "login";
        }
        return "startCooking";
    }

    @RequestMapping(path = "/rest",method = RequestMethod.GET)
    public String rest(Model model,HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登录");
            return "login";
        }
        return "rest";
    }
}
