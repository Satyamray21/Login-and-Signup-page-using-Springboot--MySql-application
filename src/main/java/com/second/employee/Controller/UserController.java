package com.second.employee.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.second.employee.Model.UserModel;
import com.second.employee.Service.UserService;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UserModel());
        return "register_Page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UserModel());
        return "login_Page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel) {
        System.out.println("register request: " + userModel);
        UserModel registeredUser = userService.registerUser(userModel.getUsername(), userModel.getPassword(), userModel.getEmail(),userModel.getFname(),userModel.getSname(),userModel.getPhone());
        
        return registeredUser == null ? "error_Page" : "redirect:/login"; 
    }
    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel,Model model) {
        System.out.println("login request: " + userModel);
        UserModel authenticated= userService.authenticate(userModel.getUsername(),userModel.getPassword());
        if(authenticated !=null){
            model.addAttribute("userLogin",authenticated.getUsername());
            return "personal_Page";
        }else{
            return "error_Page";
        }
    }
}


