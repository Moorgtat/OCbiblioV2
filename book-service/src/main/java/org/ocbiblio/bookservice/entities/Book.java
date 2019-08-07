package org.ocbiblio.bookservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    public Book(@Size(min = 1, max = 50) String titre, @Size(min = 10, max = 250) String description,
                @Size(min = 1, max = 50) String auteur, @Size(min = 10, max = 100) String image) {
        this.titre = titre;
        this.description = description;
        this.auteur = auteur;
        this.image = image;
    }

    public Long getId() { return id; }

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
}
