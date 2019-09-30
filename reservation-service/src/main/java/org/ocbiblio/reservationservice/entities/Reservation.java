package org.ocbiblio.reservationservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(unique=true)
    @Size(min = 1, max = 50)
    private String nomLivre;
    @JsonIgnore
    @OneToMany(mappedBy="reservation", fetch=FetchType.LAZY)
    private Collection<uReservation> fileReservation;
    private Boolean isFileResEmpty;
    private int BQtMax;

    public Reservation() {
    }

    public Reservation(@Size(min = 1, max = 50) String nomLivre, Boolean isFileResEmpty, int BQtMax) {
        this.nomLivre = nomLivre;
        this.isFileResEmpty = isFileResEmpty;
        this.BQtMax = BQtMax;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomLivre() {
        return nomLivre;
    }

    public void setNomLivre(String nomLivre) {
        this.nomLivre = nomLivre;
    }


    public Boolean getFileResEmpty() {
        return isFileResEmpty;
    }

    public void setFileResEmpty(Boolean fileResEmpty) {
        isFileResEmpty = fileResEmpty;
    }

    public int getBQtMax() {
        return BQtMax;
    }

    public void setBQtMax(int BQtMax) {
        this.BQtMax = BQtMax;
    }
}
