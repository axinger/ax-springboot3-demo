package com.axing.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication(exclude = {
//        DataSourceAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class,
//        MongoAutoConfiguration.class})

@SpringBootApplication
//@ComponentScan(basePackages = {"com.axing.common.response", "com.axing.common.util", "com.axing.demo"})
public class A12MinioDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(A12MinioDemoApplication.class, args);
    }

}
