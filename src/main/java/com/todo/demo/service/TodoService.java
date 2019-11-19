package com.todo.demo.service;

import com.todo.demo.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoService {

    Page<Todo> getAllTodos(Pageable pageable);
    Todo createTodo( Todo todo);
    Todo getTodoById( Long id);
    Todo updateTodo( Long id, Todo todo);
    void deleteTodo( Long id);
}
