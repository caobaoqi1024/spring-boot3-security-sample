package dev.mcdd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SpringBoot3SecuritySampleApplicationTests {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void contextLoads() {
        String encode = passwordEncoder.encode("123321");
        System.out.println("encode = " + encode);
        boolean matches = passwordEncoder.matches("123321", "$2a$10$7zfEdqQYJrBnmDdu7UkgS.zOAsJf4bB1ZYrVhCBAIvIoPbEmeVnVe");
        System.out.println("matches = " + matches);
    }

}
