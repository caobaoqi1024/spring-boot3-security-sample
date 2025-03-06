package dev.mcdd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringBoot3SecuritySampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3SecuritySampleApplication.class, args);
        log.info("SpringBoot3SecuritySampleApplication start");
    }

}
