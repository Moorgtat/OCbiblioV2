package org.ocbiblio.bookservice;

//import org.ocbiblio.bookservice.dao.BookRepository;
//import org.ocbiblio.bookservice.entities.Book;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookServiceApplication implements CommandLineRunner {

//    @Autowired
//     private BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        Book b1 = bookRepository.save(new Book("Kafka sur le rivage",
//                "Kafka Tamura s'enfuit de sa maison à Tokyo pour mener une étrange mission.",
//                "Haruki Murakami","/static/Covers/Kafka-sur-le-rivage.jpg"));
//        Book b2 = bookRepository.save(new Book("Le bûcher des vanités",
//                "La vie new-yorkaise dans sa plus glaçante réalité avec ses âmes perdus.",
//                "Tom Wolfe","/static/Covers/Le-bucher-des-vanites.jpg"));
//        Book b3 = bookRepository.save(new Book("Le Monde d'hier",
//                "Autobiographie de l'europe d'avant guerre avec ses artistes passionnés.",
//                "Stefan Zweig","/static/Covers/Le-monde-d'hier.jpg"));
//        Book b4 = bookRepository.save(new Book("L'épopée du buveur d'eau",
//                "Bogus, fumiste farfelu, a un problème, son canal urinaire est trop étroit.",
//                "John Irving","/static/Covers/L'épopée-du-buveur-d'eau.jpg"));
//        Book b5 = bookRepository.save(new Book("L'attrape Coeur",
//                "Un collégien part trois nuits à l'aventure et à la recherche de soi même.",
//                "J.D Salinger","/static/Covers/L'attrape-coeur.jpg"));
//        Book b6 = bookRepository.save(new Book("Le grand voyage",
//                "Un long voyage dont on ne revient pas sans être profondément marqué et choqué.",
//                "George Semprun","/static/Covers/Le-grand-voyage.jpg"));
//        Book b7 = bookRepository.save(new Book("Le Parfum",
//                "Grenouille tente de mettre au point à l'aide de son odorat le parfum parfait.",
//                "Patrick Süskind","/static/Covers/Le-parfum.jpg"));
//        Book b8 = bookRepository.save(new Book("Un bonheur insoutenable",
//                "Copeau poursuivra la liberté coute que coute dans ce monde rempli d'interdits.",
//                "Ira Levin","/static/Covers/Un-bonheur-insoutenable.jpg"));
//        Book b9 = bookRepository.save(new Book("Le cycle de Tschaï",
//                "Adam Reith, voyageur spatial, tente de quitter Tschaï et de regagner la Terre.",
//                "Jack Vance","/static/Covers/Le-cycle-de-Tschaï.jpg"));
//        Book b10 = bookRepository.save(new Book("Le dahlia noir",
//                "Qui a tué le dahlia noir? L'enquète du siècle ne restera pas sans réponses.",
//                "James Ellroy","/static/Covers/Le-dahlia-noir.jpg"));
//        Book b11 = bookRepository.save(new Book("L'été Diabolik",
//                "Un agent secret sorti de nulle part, un accident dramatique, un été diabolique...",
//                "Alexandre Clérisse","/static/Covers/L-été-diabolik.jpg"));

    }
}

