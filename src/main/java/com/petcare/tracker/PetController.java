package com.petcare.tracker;

import org.springframework.web.bind.annotation.*;

@RestController
public class PetController {

    @GetMapping("/hello")
    public String hello() {
        return "Pet tracker is running!";
    }
}