package top.yaoyongdou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by AAAAA on 2018-02-19.
 */
@SpringBootApplication
@ComponentScan(basePackages = "top.yaoyongdou")
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// TODO: 18-3-10 comonantscan 的问题写进笔记