package com.axing.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

    @GetMapping("/")
    public Object index() {

        Map<String, Object> map = new HashMap<>();

        map.put("LocalDateTime", LocalDateTime.now());
        map.put("Date",new Date());
        map.put("LocalDate", LocalDate.now());
        return map;
    }


}
