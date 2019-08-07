package org.ocbiblio.userservice;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {

//    @Autowired
//    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        User u1 = userRepository.save(new User("mat.lecardinal@gmail.com",
//                "Mathieu",
//                "1111"));
//
//        User u2 = userRepository.save(new User("elorna@gmail.com",
//                "Elorna",
//                "2222"));
//
//        User u3 = userRepository.save(new User("virgil@gmail.com",
//                "Virgil",
//                "3333"));
//
//        User u4 = userRepository.save(new User("anna@gmail.com",
//                "Anna",
//                "4444"));
//
//        User u5 = userRepository.save(new User("Igor@gmail.com",
//                "Igor",
//                "5555"));
    }
}

