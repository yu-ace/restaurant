package com.reasaurant.restaurant.controller;

import com.reasaurant.restaurant.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CustomerController {

    @Autowired
    ICustomerService customerService;

    @RequestMapping(path = "/customer",method = RequestMethod.POST)
    public String customer(
            @RequestParam(name = "name")
            int tableId,
            @RequestParam(name = "count")
            int count, HttpSession session){
        customerService.newCustomer(tableId,count);
        session.setAttribute("tableId",tableId);
        return "redirect:/customerAddDishes";
    }
}
