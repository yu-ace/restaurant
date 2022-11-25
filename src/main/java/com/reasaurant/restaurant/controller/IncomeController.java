package com.reasaurant.restaurant.controller;

import com.reasaurant.restaurant.model.DishesList;
import com.reasaurant.restaurant.model.Income;
import com.reasaurant.restaurant.model.User;
import com.reasaurant.restaurant.service.IIncomeService;
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
public class IncomeController {

    @Autowired
    IIncomeService incomeService;

    @RequestMapping(path = "/incomeList",method = RequestMethod.POST)
    public String incomeList(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<Income> incomeList = incomeService.getIncomeList(of);
        model.addAttribute("incomeList",incomeList);
        return "incomeList";
    }

    @RequestMapping(path = "/incomeListPage",method = RequestMethod.POST)
    public String incomeListPage(
            @RequestParam(name = "number")
            int n,
            Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(n,10);
        Page<Income> incomeList = incomeService.getIncomeList(of);
        model.addAttribute("incomeLists",incomeList);
        return "incomeList";
    }

    @RequestMapping(path = "/incomeListByTable",method = RequestMethod.POST)
    public String incomeListByTable(
            @RequestParam(name = "id")
            int id,
            @RequestParam(name = "number")
            int n,
            Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(n,10);
        Page<Income> incomeList = incomeService.getIncomeByTableId(id,of);
        model.addAttribute("incomeLists",incomeList);
        return "incomeList";
    }
}
