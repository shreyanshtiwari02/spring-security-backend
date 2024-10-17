package com.shrey.react_springboot.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shrey.react_springboot.entity.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class StudentController {

   @GetMapping("/students")
   public List<Student> students() {
    return Arrays.asList(
        new Student(1, "John Doe", "Computer Science"),
        new Student(2, "Jane Smith", "Mathematics"),
        new Student(3, "Mike Johnson", "Physics")
    );
   }
   

    
}