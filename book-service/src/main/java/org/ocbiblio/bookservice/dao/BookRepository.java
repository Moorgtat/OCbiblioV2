package org.ocbiblio.bookservice.dao;


import org.ocbiblio.bookservice.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RepositoryRestResource
public interface BookRepository extends JpaRepository <Book, Long> {

    List<Book> findByTitreIsContainingAndAuteurIsContaining(@Param("titre")String titre,
                                                            @Param("auteur")String auteur);
}
