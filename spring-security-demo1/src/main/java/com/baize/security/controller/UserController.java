package com.baize.security.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class UserController {

    @RequestMapping("/user")
    public String user(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String authority = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("username", username);
        model.addAttribute("authority", authority);
        return "user/user";
    }
    @RequestMapping("/admin")
    public String admin(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String authority = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("username", username);
        model.addAttribute("authority", authority);
        return "admin/admin";
    }
}
