package kr.ac.hansung.cse.hellospringdatajpa;

import kr.ac.hansung.cse.hellospringdatajpa.entity.MyUser;
import kr.ac.hansung.cse.hellospringdatajpa.repository.MyUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class HelloSpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringDataJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner createAdmin(MyUserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            // 관리자 계정이 존재하지 않으면 생성
            if (!userRepository.findByEmail("admin@hansung.ac.kr").isPresent()) {
                MyUser admin = new MyUser();
                admin.setEmail("admin@hansung.ac.kr");
                admin.setPassword(encoder.encode("admin"));  // BCrypt 암호화된 비밀번호
                admin.setRole("ROLE_ADMIN");

                userRepository.save(admin);
                System.out.println("✅ 관리자 계정 생성됨: admin@hansung.ac.kr / admin");
            }
        };
    }
}
