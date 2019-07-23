package de.uni.stuttgart.ipvs.em;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
        basePackages = {
                "de.uni.stuttgart.ipvs.ilv",
                "de.uni.stuttgart.ipvs.em",
        }
)
@EnableSwagger2
public class EntitiesManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntitiesManagementApplication.class, args);
    }
}
