package de.uni.stuttgart.ipvs.em;

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
public class EntitiesManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntitiesManagementApplication.class, args);
    }
}
