package com.ocbibliov2.ocbibliobatch.beans;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;

public class BookBean {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    @Size(min = 1, max = 50)
    private String titre;
    @Size(min = 1, max = 250)
    private String description;
    @Size(min = 1, max = 50)
    private String auteur;
    @Size(min = 1, max = 100)
    private String image;
    private int quantite;
    private int quantiteMax;

    public BookBean(@Size(min = 1, max = 50) String titre, @Size(min = 1, max = 250) String description, @Size(min = 1, max = 50) String auteur, @Size(min = 1, max = 100) String image, int quantite, int quantiteMax) {
        this.titre = titre;
        this.description = description;
        this.auteur = auteur;
        this.image = image;
        this.quantite = quantite;
        this.quantiteMax = quantiteMax;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getQuantiteMax() {
        return quantiteMax;
    }

    public void setQuantiteMax(int quantiteMax) {
        this.quantiteMax = quantiteMax;
    }
}

