package de.uni.stuttgart.ipvs.tm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
        basePackages = {
                "de.uni.stuttgart.ipvs.ilv",
                "de.uni.stuttgart.ipvs.tm",
        }
)
public class TopicsManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TopicsManagementApplication.class, args);
    }
}
