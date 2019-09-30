package com.ocbibliov2.ocbibliobatch.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class uReservationBean {
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
    private ReservationBean reservation;


    public uReservationBean() {
    }

    public uReservationBean(@Size(min = 1, max = 50) String nomUtilisateur, @Size(min = 1, max = 50) String nomLivre, @NotNull Date dateCreation, Date dateActivation, ReservationBean reservation, boolean actif) {
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

    public ReservationBean getReservation() {
        return reservation;
    }

    public void setReservation(ReservationBean reservation) {
        this.reservation = reservation;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}