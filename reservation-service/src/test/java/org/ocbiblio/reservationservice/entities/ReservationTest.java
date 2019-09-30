package org.ocbiblio.reservationservice.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    private Reservation reservation = new Reservation("Totoro",false,2);

    @Test
    void getNomLivre() {
        assertTrue(reservation.getNomLivre() == "Totoro");
    }

    @Test
    void setNomLivre() {
        reservation.setNomLivre("Okapi");
        assertTrue(reservation.getNomLivre() == "Okapi");
    }

    @Test
    void getFileResEmpty() {
        assertTrue(!reservation.getFileResEmpty());
    }

    @Test
    void setFileResEmpty() {
        reservation.setFileResEmpty(true);
        assertTrue(reservation.getFileResEmpty());
    }

    @Test
    void getBQtMax() {
        assertTrue(reservation.getBQtMax() == 2);
    }

    @Test
    void setBQtMax() {
        reservation.setBQtMax(3);
        assertTrue(reservation.getBQtMax() == 3);
    }
}