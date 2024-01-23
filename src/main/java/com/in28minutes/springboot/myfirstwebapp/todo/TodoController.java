package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import java.time.LocalDate;
import java.util.List;

//@Controller
@SessionAttributes("name")
public class TodoController {
    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @RequestMapping("todo-list")
    public String listOfTodos(ModelMap model){
        String userName = getLoggedInUser();
        List<Todo> todos = todoService.findByUserName(userName);
        model.put("todos",todos);
        return "listTodos";
    }
    @RequestMapping(value = "add-todo",method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model){
//        String username = (String) model.get("name");
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
//       String username = (String)model.get("name");
        String userName = getLoggedInUser();
       todoService.addNewTodo(userName,todo.getGoal(),todo.getNow(),false);
       return "redirect:todo-list";
    }
    @RequestMapping("delete-todo")
    public String removeTodo(@RequestParam int id){
        todoService.deleteTodo(id);
        return "redirect:todo-list";
    }
    @RequestMapping(value = "update-todo",method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id,ModelMap model){
        Todo todo = todoService.findById(id);
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
//        String username = (String)model.get("name");
        String userName = getLoggedInUser();
        todo.setUsername(userName);

        todoService.updateTodo(todo);
        return "redirect:todo-list";
    }
    private String getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
