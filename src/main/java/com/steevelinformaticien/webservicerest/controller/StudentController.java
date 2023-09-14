package com.steevelinformaticien.webservicerest.controller;

import com.steevelinformaticien.webservicerest.model.Student;
import com.steevelinformaticien.webservicerest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/rest" , name = "app_students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(path = "/students", name = "save")
    @ResponseStatus(HttpStatus.CREATED)
    public Student add(@RequestBody Student student) {
        return this.studentService.create(student);
    }

    @GetMapping(path = "/students", name = "list")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getListStudent() {
        return this.studentService.getAll();
    }

    @GetMapping(path = "/students/{id}", name = "read")
    @ResponseStatus(HttpStatus.OK)
    public Student findAStudent(@PathVariable Long id){
        return this.studentService.findById(id);
    }

    @PutMapping(path = "/students/{id}" , name = "update")
    @ResponseStatus(HttpStatus.OK)
    public Student update(@RequestBody Student student ,@PathVariable  Long id){
        return this.studentService.update(student,id);
    }
    @DeleteMapping(value = "/students/{id}" , name = "remove")
    public void delete(@PathVariable Long id){
        this.studentService.delete(id);
    }
}
