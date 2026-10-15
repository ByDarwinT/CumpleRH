package uce.edu.cumplerh.controller;
import uce.edu.cumplerh.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class CalendarController {
    private final EmployeeService service;
    public CalendarController(EmployeeService service){this.service=service;}
    @GetMapping("/") public String home(Model model){model.addAttribute("employees",service.findAll());return "calendar";}
}
