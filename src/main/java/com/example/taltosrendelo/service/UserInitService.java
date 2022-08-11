package com.example.taltosrendelo.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.example.taltosrendelo.entity.User;
// import com.example.taltosrendelo.repository.UserRepository;

// @Service
// public class UserInitService implements CommandLineRunner {

//     @Autowired
//     private UserRepository userRepository;
    
//     @Bean
//     PasswordEncoder passwordEncoder(){
//         return new BCryptPasswordEncoder();
//     }

//     @Override
//     public void run(String... args) throws Exception{
//         User user = new User("taltos", passwordEncoder().encode("mázli_a_főnök"), "ADMIN");
//         userRepository.save(user);
//     }
	
// }