package com.github.axinger.a.controller;

//import com.github.axinger.b.service.BService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {
    @Value("${axing.person.id}")
    private String name;

//    @Autowired
//    private BService bService;

//    @GetMapping("/")
//    public Object login() {
//        return List.of(name, bService.test());
//    }


}
