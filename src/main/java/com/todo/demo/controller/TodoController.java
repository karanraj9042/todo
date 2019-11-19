package com.todo.demo.controller;

import com.todo.demo.model.Todo;
import com.todo.demo.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class TodoController {

    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    TodoService todoService;

    @GetMapping("todos")
    public ResponseEntity getAllTodos(Pageable pageable) {

        return ResponseEntity.ok().body(todoService.getAllTodos(pageable));
    }

    @PostMapping("todos")
    public Todo createTodo(@Valid @RequestBody Todo todo) {

        return todoService.createTodo(todo);

    }

    @GetMapping(value = "todos/{id}")
    public ResponseEntity<Todo> getTodoById(
                                            @PathVariable("id") Long id) {
        if(logger.isInfoEnabled()){
            logger.info(String.format("GET TODO FOR %s %d",id));
        }
        return ResponseEntity.ok().body(todoService.getTodoById( id));

    }

    @PutMapping(value = "todos/{id}")
    public ResponseEntity<Todo> updateTodo(
                                           @PathVariable("id") Long id,
                                           @Valid @RequestBody Todo todo) {
        if(logger.isInfoEnabled()){
            logger.info(String.format("UPDATE TODO FOR %s %d",id));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(todoService.updateTodo(id, todo));
    }

    @DeleteMapping(value = "todos/{id}")
    public ResponseEntity<Void> deleteTodo(
                                           @PathVariable("id") Long id) {
        if(logger.isInfoEnabled()){
            logger.info(String.format("Delete TODO FOR %s %d",id));
        }

        todoService.deleteTodo(id);
        return ResponseEntity.ok().build();
    }


}