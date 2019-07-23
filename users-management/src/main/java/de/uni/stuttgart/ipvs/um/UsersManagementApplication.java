package de.uni.stuttgart.ipvs.um;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
        basePackages = {
                "de.uni.stuttgart.ipvs.ilv",
                "de.uni.stuttgart.ipvs.um",
        }
)
@EnableSwagger2
public class UsersManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersManagementApplication.class, args);
    }

}
