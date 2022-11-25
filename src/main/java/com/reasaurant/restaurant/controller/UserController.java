package com.reasaurant.restaurant.controller;

import com.reasaurant.restaurant.model.DishesList;
import com.reasaurant.restaurant.model.User;
import com.reasaurant.restaurant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public String login(
            @RequestParam(name = "name")
            String name,
            @RequestParam(name = "password")
            String password, Model model, HttpSession session){
        User user = userService.getUserByName(name);
        if(user == null){
            model.addAttribute("error","该用户不存在！");
            return "login";
        }
        if(user.getIsDelete() == 1){
            model.addAttribute("error","该用户已注销");
            return "login";
        }
        if(user.getPassword().equals(password)){
            if(user.getIdentity().equals("管理员")){
                session.setAttribute("user",user);
                return "redirect:/userBoard";
            }else{
                session.setAttribute("user",user);
                return "redirect/cookBoard";
            }
        }else{
            model.addAttribute("error","密码错误");
            return "login";
        }
    }

    @RequestMapping(path = "newUser",method = RequestMethod.POST)
    public String newUser(
            @RequestParam(name = "name")
            String name,
            @RequestParam(name = "password")
            String password,Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，");
            return "login";
        }
        User user1 = userService.getUserByName(name);
        if(user1 == null){
            userService.newUser(name,password);
            model.addAttribute("tip","创建成功");
            return "newUser";
        }else{
            model.addAttribute("tip","对不起，该用户名已存在，创建失败");
            return "newUser";
        }
    }

    @RequestMapping(path = "/userListPage",method = RequestMethod.POST)
    public String userListPage(
            @RequestParam(name = "number")
            int n,
            Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(n,10);
        Page<User> userList = userService.getUserList(0,of);
        model.addAttribute("userLists",userList);
        return "userList";
    }

    @RequestMapping(path = "/userListByWageGreater",method = RequestMethod.POST)
    public String userListByWageGreater(
            @RequestParam(name = "wage")
            int wage,
            @RequestParam(name = "number")
            int n,
            Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(n,10);
        Page<User> userList = userService.getUserListByWageGreater(wage,of);
        model.addAttribute("userLists",userList);
        return "userList";
    }

    @RequestMapping(path = "/userListByWageLess",method = RequestMethod.POST)
    public String userListByWageLess(
            @RequestParam(name = "wage")
            int wage,
            @RequestParam(name = "number")
            int n,
            Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(n,10);
        Page<User> userList = userService.getUserListByWageLess(wage,of);
        model.addAttribute("userLists",userList);
        return "userList";
    }

    @RequestMapping(path = "/deleteUser",method = RequestMethod.POST)
    public String deleteUser(
            @RequestParam(name = "id")
            int id,
            @RequestParam(name = "password")
            String password,
            Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        User user1 = userService.getUserById(id);
        if(user1 == null){
            model.addAttribute("tip","该用户不存在");
            return "deleteUser";
        }
        if(user1.getName().equals("admin")){
            model.addAttribute("tip","您没有权限删除该用户");
            return "deleteUser";
        }
        if(user1.getIsDelete() == 1){
            model.addAttribute("tip","该用户早已注销");
            return "deleteUser";
        }
        if(user1.getPassword().equals(password)){
            userService.deleteUser(id);
            model.addAttribute("tip","删除成功");
            return "deleteUser";
        }else{
            model.addAttribute("tip","密码错误，删除失败");
            return "deleteUser";
        }
    }

    @RequestMapping(path = "/changePassword",method = RequestMethod.POST)
    public String changePassword(
            @RequestParam(name = "name")
            String name,
            @RequestParam(name = "password")
            String password,
            @RequestParam(name = "newPassword")
            String newPassword,
            @RequestParam(name = "newPassword1")
            String newPassword1,
            Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        User user1 = userService.getUserByName(name);
        if(user1 == null){
            model.addAttribute("tip","该用户不存在");
            return "deleteUser";
        }
        if(user1.getName().equals("admin")){
            model.addAttribute("tip","您没有权限删除该用户");
            return "deleteUser";
        }
        if(user1.getIsDelete() == 1){
            model.addAttribute("tip","该用户早已注销");
            return "deleteUser";
        }
        if(user1.getPassword().equals(password)){
            if(newPassword.equals(newPassword1) && !newPassword.equals(password)){
                userService.changePassword(user1.getId(),password);
                model.addAttribute("tip","更改成功");
                return "changePassword";
            }else if(newPassword.equals(password) || newPassword1.equals(password)){
                model.addAttribute("tip","新密码和原密码相同，更改失败");
                return "changePassword";
            }else{
                model.addAttribute("tip","两次输入的新密码不同，更改失败");
                return "changePassword";
            }
        }else{
            model.addAttribute("tip","密码错误");
            return "changePassword";
        }
    }


}
