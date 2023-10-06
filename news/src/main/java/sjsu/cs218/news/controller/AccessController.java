//SJSU CS 218 SPRING 2023 TEAM4
package sjsu.cs218.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import sjsu.cs218.news.service.AccessService;

@Controller
@RequestMapping("/news/user")
public class AccessController {

    @Autowired
    AccessService accessService;

    @GetMapping("/register")
    public String getRegister(Model model){
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (accessService.isAuthenticated()) {
            return "redirect:/news";
        }
        if (error != null)
            model.addAttribute("error", "Username and password is invalid.");
        if (logout != null)
            model.addAttribute("message", "Logged out successfully.");
        return "login";
    }
    
}
