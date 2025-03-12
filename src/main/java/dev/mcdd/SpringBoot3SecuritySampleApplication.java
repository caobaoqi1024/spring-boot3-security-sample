package dev.mcdd;

import dev.mcdd.entity.User;
import dev.mcdd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@EnableJpaRepositories
@SpringBootApplication
@RequiredArgsConstructor
public class SpringBoot3SecuritySampleApplication implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public static void main(String[] args) {
//        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication app = new SpringApplication(SpringBoot3SecuritySampleApplication.class);
        app.run(args);
        log.info("SpringBoot3SecuritySampleApplication start");
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i < 10; i++) {
            userRepository.save(new User(null,"mcdd102" + i,"mcdd102" + i + "@gmail.com",passwordEncoder.encode("mcdd102" + i + "pwd"),true));
        }
    }
}
