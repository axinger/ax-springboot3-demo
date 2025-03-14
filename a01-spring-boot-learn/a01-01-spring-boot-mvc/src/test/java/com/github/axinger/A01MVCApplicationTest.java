package com.github.axinger;

import com.github.axinger.model.bean.PersonProperties;
import com.github.axinger.model.bean.UserProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class A01MVCApplicationTest {


    @Autowired
    private PersonProperties personProperties;
    @Autowired
    private UserProperties userProperties;

    @Test
    public void test() {
        System.out.println("personProperties = " + personProperties);
    }

    @Test
    public void test2() {
        System.out.println("userProperties = " + userProperties);

        String all = userProperties.all();
        System.out.println("all = " + all);

    }
}
