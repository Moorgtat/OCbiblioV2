package org.ocbiblio.reservationservice.dao;

import org.ocbiblio.reservationservice.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RepositoryRestResource
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation getByNomLivre(@Param("nomLivre")String nomLivre);
}
