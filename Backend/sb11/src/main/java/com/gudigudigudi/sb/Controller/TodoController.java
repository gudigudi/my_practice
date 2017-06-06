package com.gudigudigudi.sb.Controller;

import com.gudigudigudi.sb.Model.Todo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {

    @RequestMapping("/todos")
    public List<Todo> getAllTodos(){
        List<Todo> todos=new ArrayList<>();
        Todo item1=new Todo();

        item1.setId("1");
        item1.setDesc("go swimming");
        item1.setCompleted(false);

        todos.add(item1);

        Todo item2=new Todo();
        item2.setId("1");
        item2.setDesc("go for lunch");
        item2.setCompleted(true);

        todos.add(item2);

        return todos;
    }

}
