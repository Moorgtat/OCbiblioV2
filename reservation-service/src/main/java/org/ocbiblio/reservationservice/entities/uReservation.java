package org.ocbiblio.reservationservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class uReservation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Size(min = 1, max = 50)
    private String nomUtilisateur;
    @Size(min = 1, max = 50)
    private String nomLivre;
    @NotNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateCreation;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateActivation;
    private boolean actif;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "codeReservation")
    private Reservation reservation;


    public uReservation() {
    }

    public uReservation(@Size(min = 1, max = 50) String nomUtilisateur, @Size(min = 1, max = 50) String nomLivre, @NotNull Date dateCreation, Date dateActivation, Reservation reservation, boolean actif) {
        this.nomUtilisateur = nomUtilisateur;
        this.nomLivre = nomLivre;
        this.dateCreation = dateCreation;
        this.dateActivation = dateActivation;
        this.reservation = reservation;
        this.actif = actif;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getNomLivre() {
        return nomLivre;
    }

    public void setNomLivre(String nomLivre) {
        this.nomLivre = nomLivre;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateActivation() {
        return dateActivation;
    }

    public void setDateActivation(Date dateActivation) {
        this.dateActivation = dateActivation;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
