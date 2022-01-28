package com.quicknotes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/users/")
    public String index() {
        return "redirect:list";
    }

    @GetMapping("/users/list")
    public String getUsers(Model model) {
        model.addAttribute("message", "test");

        return "users/list";
    }

    @GetMapping("/users/add")
    public String add() {
        return "users/add";
    }

    @GetMapping("/users/view")
    public String view() {
        return "users/view";
    }

    @GetMapping("/users/edit")
    public String edit() {
        return "users/edit";
    }

    @GetMapping("/users/delete")
    public String delete() {
        return "users/delete";
    }


}
