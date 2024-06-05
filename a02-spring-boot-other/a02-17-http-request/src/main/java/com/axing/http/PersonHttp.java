package com.axing.http;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/person")
public interface PersonHttp {

    @GetExchange("/{id}")
    default Object getPerson(@PathVariable("id") String id) {
        return id;
    }
}
