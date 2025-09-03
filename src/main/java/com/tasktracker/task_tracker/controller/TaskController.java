package com.tasktracker.task_tracker.controller;

import com.tasktracker.task_tracker.entity.*;
import com.tasktracker.task_tracker.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    // Constructor Injection (Spring will inject TaskServiceImpl)
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // ✅ List all tasks
    @GetMapping
    public ModelAndView listTasks() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("tasks", taskService.findAll());
        return mav;
    }

    // ✅ Show form to create a new task
    @GetMapping("/new")
    public ModelAndView newTaskForm() {
        ModelAndView mav = new ModelAndView("task-form");
        mav.addObject("task", new Task());
        return mav;
    }

    // ✅ Save new or updated task
    @PostMapping
    public ModelAndView saveTask(@ModelAttribute("task") Task task) {
        taskService.save(task);
        return new ModelAndView("redirect:/tasks");
    }

    // ✅ Show form to edit an existing task
    @GetMapping("/edit/{id}")
    public ModelAndView editTaskForm(@PathVariable Long id) {
        Task task = taskService.findById(id);
        if (task != null) {
            ModelAndView mav = new ModelAndView("task-form");
            mav.addObject("task", task);
            return mav;
        }
        return new ModelAndView("redirect:/tasks");
    }

    // ✅ Delete a task
    @GetMapping("/delete/{id}")
    public ModelAndView deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return new ModelAndView("redirect:/tasks");
    }

    // ✅ Update task status (Not Started, In Progress, Done)
    @GetMapping("/status/{id}/{status}")
    public ModelAndView updateStatus(@PathVariable Long id, @PathVariable Status status) {
        Task task = taskService.findById(id);
        if (task != null) {
            task.setStatus(status);
            taskService.save(task);
        }
        return new ModelAndView("redirect:/tasks");
    }
}
