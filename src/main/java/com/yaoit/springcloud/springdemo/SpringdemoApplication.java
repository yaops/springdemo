package com.yaoit.springcloud.springdemo;

import com.yaoit.springcloud.springdemo.thread.scrapyJd.spiderHandler.SpiderHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = {"com.yaoit.springcloud.springdemo.thread"})
public class SpringdemoApplication {
    @Autowired
    private SpiderHandler spiderHandler;
    public static void main(String[] args) {
        SpringApplication.run(SpringdemoApplication.class, args);
    }
    @PostConstruct
    public void task() {
        spiderHandler.spiderData();
    }
}
