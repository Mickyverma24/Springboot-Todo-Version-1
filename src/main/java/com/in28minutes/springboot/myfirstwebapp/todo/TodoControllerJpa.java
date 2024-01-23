package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
    private TodoService todoService;
    private TodoRepository todoRepository;

    @Autowired
    public TodoControllerJpa(TodoService todoService,TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }
    @RequestMapping("todo-list")
    public String listOfTodos(ModelMap model){
        String username = getLoggedInUser();
        List<Todo> todos = todoRepository.findByUsername(username);
        model.put("todos",todos);
        return "listTodos";
    }
    @RequestMapping(value = "add-todo",method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model){
        String userName = getLoggedInUser();
        Todo todo = new Todo(0,userName,"",LocalDate.now().plusYears(1),false);
        model.put("todo",todo);
        return "todo";
    }
    @RequestMapping(value = "add-todo",method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            System.out.println("We get some errors here buddy");
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors ) {
                System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
            }
            return "todo";
        }
        String username = getLoggedInUser();
        todo.setUsername(username);
        todoRepository.save(todo);

       return "redirect:todo-list";
    }
    @RequestMapping("delete-todo")
    public String removeTodo(@RequestParam int id){

        todoRepository.deleteById(id);
        return "redirect:todo-list";
    }

    @RequestMapping(value = "update-todo",method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id,ModelMap model){
        Todo todo = todoRepository.findById(id).get();
        model.addAttribute("todo",todo);
        return "todo";
    }
    @RequestMapping(value = "update-todo",method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            System.out.println("We get some errors here buddy");
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors ) {
                System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
            }
            return "todo";
        }
        String username = getLoggedInUser();
        todo.setUsername(username);

        todoRepository.save(todo);
        return "redirect:todo-list";
    }
    private String getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
