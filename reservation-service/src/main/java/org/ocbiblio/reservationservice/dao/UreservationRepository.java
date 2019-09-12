package org.ocbiblio.reservationservice.dao;

import org.ocbiblio.reservationservice.entities.uReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RepositoryRestResource
public interface UreservationRepository extends JpaRepository<uReservation, Long> {
    List <uReservation> findAllByNomUtilisateurAndActifIsTrueOrderByIdAsc(@Param("nomUtilisateur")String nomUtilisateur);
    List <uReservation> findAllByNomUtilisateurOrderByIdAsc(@Param("nomUtilisateur")String nomUtilisateur);
    List <uReservation> findAllByNomLivre(@Param("nomLivre")String nomLivre);
    List <uReservation> findAllByNomLivreAndNomUtilisateur(@Param("nomLivre")String nomLivre, @Param("nomUtilisateur")String nomUtilisateur);
    uReservation findFirstByNomLivreAndActifIsFalseOrderByDateCreationAsc(@Param("nomLivre")String nomLivre);
    List <uReservation> findAllByNomUtilisateur(@Param("nomUtilisateur") String nomUtilidateur);
}
