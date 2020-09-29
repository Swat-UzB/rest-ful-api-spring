package com.example.restfullapi.controller;

import com.example.restfullapi.model.HelloWorldBean;
import com.example.restfullapi.util.Mappings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // == request methods ==
    // http://localhost:8082/hello-world
    @GetMapping(Mappings.HELLO_WORLD)
    public String helloWorld() {
        return "Hello world";
    }

    // http://localhost:8082/hello-world-bean
    @GetMapping(Mappings.HELLO_WORLD_BEAN)
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello world");
    }

    // http://localhost:8082/hello-world/path-variable/Hello
    @GetMapping(Mappings.HELLO_WORLD_BEAN_PATH_VRIABLE)
    public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Salom %s", name));
    }
}
