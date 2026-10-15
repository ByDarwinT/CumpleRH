package uce.edu.cumplerh.controller;
import uce.edu.cumplerh.model.Employee;
import uce.edu.cumplerh.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/employees")
public class EmployeeController {
 private final EmployeeService service;
 public EmployeeController(EmployeeService service){this.service=service;}
 @GetMapping public String list(@RequestParam(required=false,defaultValue="") String query,Model model){
   model.addAttribute("employees",service.search(query));model.addAttribute("query",query);return "employees";
 }
 @GetMapping("/new") public String newEmployee(Model model){model.addAttribute("employee",new Employee());prepareForm(model,false);return "form";}
 @GetMapping("/{nationalId}") public String details(@PathVariable String nationalId,Model model){model.addAttribute("employee",service.findByNationalId(nationalId));return "details";}
 @GetMapping("/edit/{nationalId}") public String edit(@PathVariable String nationalId,Model model){model.addAttribute("employee",service.findByNationalId(nationalId));prepareForm(model,true);return "form";}
 @PostMapping("/save") public String save(@Valid @ModelAttribute("employee") Employee employee,BindingResult errors,
    @RequestParam(defaultValue="false") boolean editing,Model model,RedirectAttributes flash){
    if(!editing&&service.exists(employee.getNationalId())) errors.rejectValue("nationalId","duplicado","An employee with this ID already exists");
    if(editing&&!service.exists(employee.getNationalId())) errors.rejectValue("nationalId","notFound","Employee not found");
    if(!service.isValid(employee)) errors.reject("datos","Check the dates, department, and gender");
    if(errors.hasErrors()){prepareForm(model,editing);return "form";}
    service.save(employee);flash.addFlashAttribute("message",editing?"Employee updated successfully":"Employee registered successfully");
    return "redirect:/employees";
 }
 @PostMapping("/delete/{nationalId}") public String delete(@PathVariable String nationalId,RedirectAttributes flash){
    if(service.exists(nationalId)){service.delete(nationalId);flash.addFlashAttribute("message","Employee deleted successfully");}
    return "redirect:/employees";
 }
 private void prepareForm(Model model,boolean editing){
    model.addAttribute("editing",editing);model.addAttribute("departments",EmployeeService.DEPARTMENTS);model.addAttribute("genders",EmployeeService.GENDERS);
 }
 @ExceptionHandler(IllegalArgumentException.class) public String notFound(RedirectAttributes flash){
    flash.addFlashAttribute("error","The requested employee could not be found");return "redirect:/employees";
 }
}
