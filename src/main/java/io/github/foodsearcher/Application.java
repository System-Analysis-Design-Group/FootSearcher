package io.github.foodsearcher;

import io.github.foodsearcher.config.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by AAAAA on 2018-02-19.
 */


@SpringBootApplication(exclude = {})
@ComponentScan(basePackages = "io.github.foodsearcher")
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
