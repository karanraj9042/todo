package com.todo.demo.service;

import com.todo.demo.exception.ResourceNotFoundException;
import com.todo.demo.model.Todo;
import com.todo.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Page<Todo> getAllTodos( Pageable pageable) {
        return todoRepository.findAll(pageable);
    }

    @Override
    public Todo createTodo( Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo getTodoById( Long id) {
        return todoRepository.findById(id).get();
    }

    @Override
    public Todo updateTodo( Long id,  Todo todo) {
       return todoRepository.findById(id).map(todoRequest -> {
            todoRequest.setDescription(todo.getDescription());
            todoRequest.setCompleted(todo.getCompleted());
            return todoRepository.save(todoRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Todo", "id", id));
    }

    @Override
    public void deleteTodo( Long id) {

        Todo todoData = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", id));
        todoRepository.delete(todoData);
    }

}
