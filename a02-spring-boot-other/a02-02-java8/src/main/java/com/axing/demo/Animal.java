package com.axing.demo;

import lombok.Data;

@Data
public abstract class Animal {

    String name;

    abstract void eat();

    void eat1() {

    }

}
