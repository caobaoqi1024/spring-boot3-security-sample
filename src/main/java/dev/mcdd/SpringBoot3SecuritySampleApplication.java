package dev.mcdd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringBoot3SecuritySampleApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBoot3SecuritySampleApplication.class);
        app.run(args);
        log.info("SpringBoot3SecuritySampleApplication start");
    }

}
