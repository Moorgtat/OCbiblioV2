package org.ocbiblio.loanservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Loan {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date debutPret;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date finPret;
    private String nomLivre;
    private String pseudoEmprunteur;
    private Boolean authProlong;

    public Loan(Date debutPret, Date finPret, String nomLivre, String pseudoEmprunteur, Boolean authProlong) {
        this.debutPret = debutPret;
        this.finPret = finPret;
        this.nomLivre = nomLivre;
        this.pseudoEmprunteur = pseudoEmprunteur;
        this.authProlong = authProlong;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDebutPret() {
        return debutPret;
    }

    public void setDebutPret(Date debutPret) {
        this.debutPret = debutPret;
    }

    public Date getFinPret() {
        return finPret;
    }

    public void setFinPret(Date finPret) {
        this.finPret = finPret;
    }

    public String getNomLivre() {
        return nomLivre;
    }

    public void setNomLivre(String nomLivre) {
        this.nomLivre = nomLivre;
    }

    public String getPseudoEmprunteur() {
        return pseudoEmprunteur;
    }

    public void setPseudoEmprunteur(String pseudoEmprunteur) {
        this.pseudoEmprunteur = pseudoEmprunteur;
    }

    public Boolean getAuthProlong() {
        return authProlong;
    }

    public void setAuthProlong(Boolean authProlong) {
        this.authProlong = authProlong;
    }
}
