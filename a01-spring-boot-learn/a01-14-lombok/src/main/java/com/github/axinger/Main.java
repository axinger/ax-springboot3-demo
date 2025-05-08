package com.github.axinger;

import com.axing.common.util.json.JsonUtil;
import com.github.axinger.model.GetterSetterExample;
import com.github.axinger.model.Person;
import lombok.SneakyThrows;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {

        Person person = new Person();

        person.aFirst("aFirst");


        String string = JsonUtil.writeValueAsString(person);
        System.out.println("string = " + string);
    }
}


class Main2 {
    public static void main(String[] args) {


        GetterSetterExample example = new GetterSetterExample();
        example.setAge(1);

    }
}


class Main3 {
    public static void main(String[] args) {
        Person person = new Person();
        // person.setAddress(null);
        person.setAddress("aaa");
        System.out.println("person = " + person);

    }
}
