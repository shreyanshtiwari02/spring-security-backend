package com.shrey.react_springboot.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloController {
    
    @GetMapping("/")
    public ResponseEntity<String> getMethodName(HttpServletRequest req) {
        // Get the current session ID
        String sessionId = req.getSession().getId();
        
        // Return the session ID wrapped in a ResponseEntity with a status code
        return ResponseEntity.ok(sessionId);
    }
    
    // @GetMapping("/")
    // public String getMethodName(HttpServletRequest  req) {
    
    //     return  req.getSession().getId();
    // }


}
