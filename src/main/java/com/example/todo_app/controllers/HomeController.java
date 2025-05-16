package com.example.todo_app.controllers;

import com.example.todo_app.models.TodoItem;
import com.example.todo_app.services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", todoItemService.getAll());
        modelAndView.addObject("newTodoItem", new TodoItem());
        return modelAndView;
    }

    @PostMapping("/todo/add")
    public String addTodoItem(@RequestParam("title") String title, 
                              @RequestParam("description") String description) {
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle(title);
        todoItem.setDescription(description);
        todoItem.setCompleted(false);
        todoItemService.save(todoItem);
        return "redirect:/";
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id) {
        todoItemService.getById(id).ifPresent(todoItem -> {
            todoItemService.delete(todoItem);
        });
        return "redirect:/";
    }

    @GetMapping("/todo/edit/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Optional<TodoItem> todoItem = todoItemService.getById(id);
        if (todoItem.isPresent()) {
            modelAndView.addObject("todoItem", todoItem.get());
        } else {
            // If item not found, redirect to home
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

    @PostMapping("/todo/update")
    public String updateTodoItem(@RequestParam("id") Long id,
                                @RequestParam("title") String title,
                                @RequestParam("description") String description) {
        Optional<TodoItem> todoItemOpt = todoItemService.getById(id);
        if (todoItemOpt.isPresent()) {
            TodoItem todoItem = todoItemOpt.get();
            todoItem.setTitle(title);
            todoItem.setDescription(description);
            // Don't change the completed status or creation date
            todoItemService.save(todoItem);
        }
        return "redirect:/";
    }

    @GetMapping("/todo/toggle/{id}")
    public String toggleTodoItem(@PathVariable("id") Long id) {
        Optional<TodoItem> todoItemOpt = todoItemService.getById(id);
        if (todoItemOpt.isPresent()) {
            TodoItem todoItem = todoItemOpt.get();
            todoItem.setCompleted(!todoItem.getCompleted());
            todoItemService.save(todoItem);
        }
        return "redirect:/";
    }
}
