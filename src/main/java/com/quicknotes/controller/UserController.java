package com.quicknotes.controller;

import com.quicknotes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    static final int DEFAULT_PAGE_SIZE = 2;

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users/")
    public String index() {
        return "redirect:list";
    }

    @GetMapping("/users/list")
    public String getUsers(final Model model,
                           @RequestParam(value = "page", defaultValue = "0") final int pageNumber,
                           @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE + "") final int pageSize) {

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
