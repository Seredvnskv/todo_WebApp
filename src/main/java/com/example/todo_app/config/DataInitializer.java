package com.example.todo_app.config;

import com.example.todo_app.models.TodoItem;
import com.example.todo_app.repositories.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Override
    public void run(String... args) {
        // Check if we already have data
        if (todoItemRepository.count() == 0) {
            // Create sample todo items
            TodoItem item1 = new TodoItem();
            item1.setTitle("Complete Spring Boot Tutorial");
            item1.setDescription("Finish the Spring Boot tutorial and implement a to-do application");
            item1.setCompleted(false);
            item1.setCreatedAt(Instant.now());
            item1.setUpdatedAt(Instant.now());

            TodoItem item2 = new TodoItem();
            item2.setTitle("Learn Tailwind CSS");
            item2.setDescription("Study Tailwind CSS documentation and implement it in the to-do app");
            item2.setCompleted(true);
            item2.setCreatedAt(Instant.now().minusSeconds(86400)); // 1 day ago
            item2.setUpdatedAt(Instant.now());

            TodoItem item3 = new TodoItem();
            item3.setTitle("Deploy Application");
            item3.setDescription("Deploy the to-do application to a cloud platform");
            item3.setCompleted(false);
            item3.setCreatedAt(Instant.now().minusSeconds(172800)); // 2 days ago
            item3.setUpdatedAt(Instant.now());

            // Save all items
            todoItemRepository.saveAll(Arrays.asList(item1, item2, item3));
            
            System.out.println("Sample data initialized!");
        }
    }
}