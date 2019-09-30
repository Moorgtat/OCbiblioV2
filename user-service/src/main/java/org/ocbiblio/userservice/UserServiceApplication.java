package org.ocbiblio.userservice;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {

//    @Autowired
//    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        User u1 = userRepository.save(new User("41f8009cd2-c01bbd@inbox.mailtrap.io",
//                "Mathieu",
//                "1111"));
//
//        User u2 = userRepository.save(new User("41f8009cd2-c01bbd@inbox.mailtrap.io",
//                "Elorna",
//                "2222"));
//
//        User u3 = userRepository.save(new User("41f8009cd2-c01bbd@inbox.mailtrap.io",
//                "Virgil",
//                "3333"));
//
//        User u4 = userRepository.save(new User("41f8009cd2-c01bbd@inbox.mailtrap.io",
//                "Anna",
//                "4444"));
//
//        User u5 = userRepository.save(new User("41f8009cd2-c01bbd@inbox.mailtrap.io",
//                "Igor",
//                "5555"));
    }
}

