package com.jscode.projectclasssmartsangmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ProjectClassSmartSangminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectClassSmartSangminApplication.class, args);
    }

}
