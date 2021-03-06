package org.ocbiblio.bookservice.controller;

import org.ocbiblio.bookservice.dao.BookRepository;
import org.ocbiblio.bookservice.entities.Book;
import org.springframework.web.bind.annotation.*;
import java.nio.file.*;
import java.util.List;

@CrossOrigin
@RestController
public class BookRestController {

    private BookRepository bookRepository;
    BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/allBooks")
    public List<Book> allBooks () {
    return bookRepository.findAll();
    }

    @RequestMapping("/searchBooks/")
    public List <Book> ListBooks (@RequestParam(name="titre", defaultValue = "")String titre,
                                     @RequestParam(name="auteur", defaultValue = "")String auteur){
        return bookRepository.findByTitreIsContainingAndAuteurIsContaining(titre, auteur);
    }

    @GetMapping("/getBookQuantity/")
    public int bookQuantity(@RequestParam(name="titre", defaultValue = "")String titre){
        Book book = bookRepository.findBookByTitre(titre);
        int bookQuantity = book.getQuantite();
        return bookQuantity;
    }

    @PatchMapping("/bookPlusOne")
    Book bookplus(@RequestParam(name="titre", defaultValue = "")String titre) {
        Book book = bookRepository.findBookByTitre(titre);
        int quantiteActuel = book.getQuantite();
        if (quantiteActuel >= book.getQuantiteMax()) {
            System.out.println("La bibliothèque ne contient pas autant d'exemplaire de cet ouvrage");
        } else {
            book.setQuantite(quantiteActuel + 1);
        }
        return bookRepository.save(book);
    }

    @PatchMapping("/bookMinorOne/")
    Book bookminor(@RequestParam(name="titre", defaultValue = "")String titre){
        Book book = bookRepository.findBookByTitre(titre);
        int quantiteActuel = book.getQuantite();
        if (quantiteActuel < 1) {
            System.out.println("L'ouvrage n'est plus en Stock");
        } else {
            book.setQuantite(quantiteActuel - 1);
        }
        return bookRepository.save(book);
    }

    @PostMapping("/createBook")
    Book book(@RequestParam(name="titre", defaultValue = "")String titre,
              @RequestParam(name="description", defaultValue = "") String description,
              @RequestParam(name="auteur", defaultValue = "")String auteur,
              @RequestParam(name="image", defaultValue = "") String image){
        String uiCoverDirectory = "/static/Covers/";
        String imageLinkForBdd = uiCoverDirectory + image;
        Book book = new Book (titre, description, auteur, imageLinkForBdd, 2, 2);
//      J'ai décidé d'implémenter une méthode plus légère et plus simple au niveau du code pour
//      l'upload de cover mais qui oblige à uploader ses covers à partir du même dossier.
        Path source = Paths.get( "C:\\Users\\MOORGTAT\\Desktop\\Docs\\ArtS\\Book-Cover\\" + image);
        Path target = Paths.get("C:\\Users\\MOORGTAT\\Java-DEV\\Workspace\\ocbiblio\\vue-ui\\static\\Covers\\");
        try {
            Files.copy(source, target.resolve(image), StandardCopyOption.REPLACE_EXISTING);
        }catch(Exception e){
            e.printStackTrace();
        }
        return bookRepository.save(book);
    }

}
