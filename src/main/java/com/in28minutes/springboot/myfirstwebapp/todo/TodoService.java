package com.in28minutes.springboot.myfirstwebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    static private List<Todo> todos = new ArrayList<>();

    static private int idCounter = 0;

    static
    {
        todos.add(new Todo( ++idCounter ,"ravi","learnSpring", LocalDate.now().plusMonths(1),false));
        todos.add(new Todo( ++idCounter ,"ravi","learnGraph", LocalDate.now().plusDays(5),false));
        todos.add(new Todo( ++idCounter ,"ravi","learnDp", LocalDate.now().plusMonths(1),false));
        todos.add(new Todo( ++idCounter ,"ravi","learnCommunication", LocalDate.now().plusMonths(2),false));
    }
    public List<Todo> findByUserName(String userName){
        Predicate<? super Todo> predicate = todo->todo.getUsername().equalsIgnoreCase(userName);
        return todos.stream().filter(predicate).toList();
    }
    public void addNewTodo(String username,String goal,LocalDate date,boolean done){
        Todo todo = new Todo(++idCounter,username,goal,date,done);
        todos.add(todo);
    }
    public void deleteTodo(int id){
        System.out.println("Todo is removed by the client");
        Predicate<? super Todo> predicate
                = todo -> todo.getId()==id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        // use predicate here
        Predicate<? super Todo> predicate
                = todo -> todo.getId()==id;
        return todos.stream().filter(predicate).findFirst().get();
    }

    public void updateTodo(Todo todo) {
        deleteTodo(todo.getId());
        todos.add(todo);
    }
}
