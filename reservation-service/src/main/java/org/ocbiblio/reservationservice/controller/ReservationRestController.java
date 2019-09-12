package org.ocbiblio.reservationservice.controller;

import org.ocbiblio.reservationservice.dao.ReservationRepository;
import org.ocbiblio.reservationservice.dao.UreservationRepository;
import org.ocbiblio.reservationservice.entities.Reservation;
import org.ocbiblio.reservationservice.entities.uReservation;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class ReservationRestController {

    private ReservationRepository reservationRepository;
    private UreservationRepository ureservationRepository;

    ReservationRestController(ReservationRepository reservationRepository, UreservationRepository ureservationRepository) {
        this.reservationRepository = reservationRepository;
        this.ureservationRepository = ureservationRepository;
    }

    // Créer Reservation
    @PostMapping("/createRes/")
    public Reservation reservation(@RequestParam(name = "nomLivre", defaultValue = "") String nomLivre){
        Reservation res = new Reservation(nomLivre, true, 2);
        return reservationRepository.save(res);
    }

    // Créer uReservation
    @PostMapping("/addRes/")
    public uReservation newUres(@RequestParam(name = "nomUtil", defaultValue = "")String nomUtil,
                             @RequestParam(name = "nomLivre", defaultValue = "")String nomLivre){
        List <uReservation> listByNomLivre = ureservationRepository.findAllByNomLivre(nomLivre) ;
        Reservation reservation = reservationRepository.getByNomLivre(nomLivre);
        if (listByNomLivre.size() >= reservation.getBQtMax() * 2)
          return null;
         else {
            uReservation ures = new uReservation(nomUtil, nomLivre, new Date(), null, reservation, false);
            return ureservationRepository.save(ures);
        }
    }

    // Passe le booléen de verification de la FA en false
    @PatchMapping("/filAttNotEmpty/")
    public Reservation resNotEmpty(@RequestParam(name = "nomLivre", defaultValue = "")String nomLivre){
        Reservation reservation = reservationRepository.getByNomLivre(nomLivre);
        reservation.setFileResEmpty(false);
        return reservationRepository.save(reservation);
    }

    // Passe le booléen de verification de la FA en true
    @PatchMapping("/filAttEmpty/")
    public Reservation resEmpty(@RequestParam(name = "nomLivre", defaultValue = "")String nomLivre){
        Reservation reservation = reservationRepository.getByNomLivre(nomLivre);
        reservation.setFileResEmpty(true);
        return reservationRepository.save(reservation);
    }

    // Supprimer une uReservation
    @GetMapping("/DeleteRes/")
    public void DeleteRes(@RequestParam(name = "id", defaultValue = "") long id) {
       uReservation ures  = ureservationRepository.getOne(id);
       ureservationRepository.delete(ures);
}

    // Afficher la liste de uReservation d'un utilisateur
    @RequestMapping("/FileAttente/")
    public List<uReservation> fileAttente(@RequestParam(name= "nomUtil", defaultValue = "") String nomUtil){
       List <uReservation> fileAttente = ureservationRepository.findAllByNomUtilisateurOrderByIdAsc(nomUtil);
       return fileAttente;
}
    // Afficher la liste de uReservation par nomLivre
    @RequestMapping("/listResNomLivre/")
    public List<uReservation> listResNomLivre(@RequestParam(name= "nomLivre", defaultValue = "") String nomLivre){
        List <uReservation> listeResNomLivre = ureservationRepository.findAllByNomLivre(nomLivre);
        return listeResNomLivre;
    }

    // Afficher la liste de uReservation par utilisateur et nomlivre
    @RequestMapping("/listeResByNomlivreAndNomUtil/")
    public List<uReservation> listeResByNomlivreNomUtil(@RequestParam(name = "nomLivre", defaultValue = "")String nomLivre,
                                                     @RequestParam(name= "nomUtil", defaultValue = "") String nomUtil){
        return ureservationRepository.findAllByNomLivreAndNomUtilisateur(nomLivre, nomUtil);
    }

    // Afficher la liste de uReservation active d'un utilisateur
    @RequestMapping("/FileResActMessage/")
    public List<uReservation> fileResAct(@RequestParam(name= "nomUtil", defaultValue = "") String nomUtil){
        List <uReservation> fileResActMess = ureservationRepository.findAllByNomUtilisateurAndActifIsTrueOrderByIdAsc(nomUtil);
        return fileResActMess;
    }

    // Obtenir la uReservation la plus ancienne d'un utilisateur
    @PatchMapping("/getOldUres/")
    uReservation ures (@RequestParam(name="nomLivre", defaultValue = "") String nomLivre){
        uReservation ures = ureservationRepository.findFirstByNomLivreAndActifIsFalseOrderByDateCreationAsc(nomLivre);
        ures.setActif(true);
        ures.setDateActivation(new Date());
        ureservationRepository.save(ures);
        return ures;
    }

    // Compte le nombre de uReservartion par utilisateur
    @RequestMapping("/CountuResByUser/")
    public int CountLoansForCount (@RequestParam(name = "nomUtilisateur", defaultValue = "") String nomUtilisateur) {
        List ListuResForCount = ureservationRepository.findAllByNomUtilisateur(nomUtilisateur);
        int countuRes = ListuResForCount.size();
        return countuRes;
    }

    // Methode estimation Temps

    // Methode Batch

}
