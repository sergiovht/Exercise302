package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    TaskRepository taskRepository;

    @RequestMapping("/")
    public String listTasks(Model model){
        model.addAttribute("tasks", taskRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String jobForm(Model model){
        model.addAttribute("task", new Task());
        return "taskform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Task task, BindingResult result){
        if (result.hasErrors()){
            return "taskform";
        }
        taskRepository.save(task);
        return "redirect:/";
    }
}
