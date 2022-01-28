package com.quicknotes;

import static java.util.UUID.randomUUID;

import com.quicknotes.entity.User;
import com.quicknotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class QuickNotesApplication {

    @Autowired
    private UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(QuickNotesApplication.class, args);

    }

    @Bean
    public ApplicationRunner initializeUsers() {

        final User defaultUser1 = new User(randomUUID(),"qwerty123", "password");
        final User defaultUser2 = new User(randomUUID(),"asdf123", "password");

        return args -> userRepository.saveAll(Arrays.asList(defaultUser1,defaultUser2));
    }


}
