package org.ocbiblio.reservationservice.entities;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class uReservationTest {

   Reservation reservation = new Reservation("Totoro", false, 2);
   uReservation ureservation = new uReservation("Mathieu", "Totoro", new Date(), new Date(), reservation, true);

    @Test
    void getNomUtilisateur() {
        assertTrue(ureservation.getNomUtilisateur().equals("Mathieu"));
    }

    @Test
    void setNomUtilisateur() {
        ureservation.setNomUtilisateur("Marc");
        assertTrue(ureservation.getNomUtilisateur().equals("Marc"));
    }

    @Test
    void getNomLivre() {
        assertTrue(ureservation.getNomLivre().equals("Totoro"));
    }

    @Test
    void setNomLivre() {
        ureservation.setNomLivre("Okapi");
        assertTrue(ureservation.getNomLivre().equals("Okapi"));
    }

    @Test
    void getDateCreation() {
        assertFalse(ureservation.getDateCreation().equals(new Date(25/10/2018)));
    }

    @Test
    void setDateCreation() {
        ureservation.setDateCreation(new Date());
        assertTrue(ureservation.getDateCreation().equals(new Date()));
    }

    @Test
    void getDateActivation() {
        assertFalse(ureservation.getDateActivation().equals(new Date(25/10/2018)));
    }

    @Test
    void setDateActivation() {
        ureservation.setDateActivation(new Date());
        assertTrue(ureservation.getDateActivation().equals(new Date()));
    }

    @Test
    void isActif() {
        assertTrue(ureservation.isActif());
    }

    @Test
    void setActif() {
        ureservation.setActif(false);
        assertTrue(!ureservation.isActif());
    }
}