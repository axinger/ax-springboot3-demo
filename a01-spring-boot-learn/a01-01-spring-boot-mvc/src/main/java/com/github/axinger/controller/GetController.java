package com.github.axinger.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/getTest")
@Validated // get请求校验参数,不封装
public class GetController {

    @GetMapping("/login")
    public Object login(@RequestParam @NotEmpty(message = "username不能为空") String username,
                        @RequestParam @NotBlank(message = "password不能为空") @Length(min = 2, message = "password长度不能小于2") String password) {
        return List.of(username, password);
    }


}
