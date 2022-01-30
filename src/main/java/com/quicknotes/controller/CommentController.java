package com.quicknotes.controller;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.quicknotes.entity.Comment;
import com.quicknotes.repository.CommentRepository;
import com.quicknotes.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/comments")
public class CommentController {


    static final int DEFAULT_PAGE_SIZE = 2;

    private final CommentService service;
    private final CommentRepository repository;

    @Autowired
    public CommentController(CommentService service, CommentRepository repository) {
        this.service = service;
        this.repository = repository;
    }


    private Sort.Direction getSortingDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }


    @GetMapping("/sortedcomments")
    public ResponseEntity<List<Comment>> getAllComments(@RequestParam(defaultValue = "id, desc") String[] sort) {

        try {
            List<Order> orders = new ArrayList<Order>();

            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"

                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(getSortingDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field,direction]
                orders.add((new Order(getSortingDirection(sort[1]), sort[0])));
            }

            List<Comment> comments = (List<Comment>) repository.findAll(Sort.by(orders));

            if (comments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/comments/list")
    public String list(final Model model, @RequestParam(value = "page", defaultValue = "0") final int pageNumber,
                       @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE + "") final int pageSize) {

        final Page<Comment> page = service.getComments(pageNumber, pageSize);

        final int currentPageNumber = page.getNumber();
        final int previousPageNumber = page.hasPrevious() ? currentPageNumber - 1 : -1;
        final int nextPageNumber = page.hasNext() ? currentPageNumber + 1 : -1;


        model.addAttribute("comments", page.getContent());
        model.addAttribute("previousPageNumber", previousPageNumber);
        model.addAttribute("nextPageNumber", nextPageNumber);
        model.addAttribute("currentPageNumber", currentPageNumber);
        model.addAttribute("pageSize", pageSize);


        return "comments/list";

    }

    @GetMapping("/comments/view")
    public String view(Model model, @RequestParam final int id) {

        final Optional<Comment> record = service.getComment(id);

        model.addAttribute("comment", record.isPresent() ? record.get() : new Comment());
        model.addAttribute("id", id);

        return "student/view";

    }


    @GetMapping("/comments/add")
    public String add(Model model) {

        model.addAttribute("comment", new Comment());

        return "comments/add";
    }

    @GetMapping("comments/edit")
    public String edit(Model model, @RequestParam final int id) {

        final Optional<Comment> record = service.getComment(id);

        model.addAttribute("comment", record.isPresent() ? record.get() : new Comment());
        model.addAttribute("id", id);

        return "comments/edit";


    }

    @GetMapping("comments/delete")
    public String delete(Model model, @RequestParam final int id) {

        service.delete(id);

        return "redirect:list";


    }

    @PostMapping("comments/save")
    public String save(Model model, @ModelAttribute Comment comment, final BindingResult errors) {

        service.save(comment);

        return "redirect:list";

    }


}



