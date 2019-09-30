package org.ocbiblio.reservationservice.controller;

import org.ocbiblio.reservationservice.mail.SendEmail;
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
    public Reservation reservation(@RequestParam(name = "nomLivre", defaultValue = "") String nomLivre) {
        Reservation res = new Reservation(nomLivre, true, 2);
        return reservationRepository.save(res);
    }

    // Créer uReservation
    @PostMapping("/addRes/")
    public uReservation newUres(@RequestParam(name = "nomUtil", defaultValue = "") String nomUtil,
                                @RequestParam(name = "nomLivre", defaultValue = "") String nomLivre) {
        List<uReservation> listByNomLivre = ureservationRepository.findAllByNomLivre(nomLivre);
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
    public Reservation resNotEmpty(@RequestParam(name = "nomLivre", defaultValue = "") String nomLivre) {
        Reservation reservation = reservationRepository.getByNomLivre(nomLivre);
        reservation.setFileResEmpty(false);
        return reservationRepository.save(reservation);
    }

    // Passe le booléen de verification de la FA en true
    @PatchMapping("/filAttEmpty/")
    public Reservation resEmpty(@RequestParam(name = "nomLivre", defaultValue = "") String nomLivre) {
        Reservation reservation = reservationRepository.getByNomLivre(nomLivre);
        reservation.setFileResEmpty(true);
        return reservationRepository.save(reservation);
    }

    // Supprimer une uReservation
    @GetMapping("/DeleteRes/")
    public void DeleteRes(@RequestParam(name = "id", defaultValue = "") long id) {
        uReservation ures = ureservationRepository.getOne(id);
        ureservationRepository.delete(ures);
    }

    // methode batch supprime res apres 48h activite
    @GetMapping("/batchMethod/")
    public void deleteResBatch() {
        uReservation ures = ureservationRepository.findFirstByActifTrueOrderByIdAsc();
        Date da = ures.getDateActivation();
        long addtime = da.getTime() + 2 * 24 * 60 * 60 * 1000;
        Date daPlus = new Date(addtime);
        if(daPlus.before(new Date())){
            ureservationRepository.delete(ures);}
    }

//    @PatchMapping("/patchbatchMethod/")
//    public void deleteResPatchBatch() {
//        uReservation ures = ureservationRepository.findFirstByActifTrueOrderByIdAsc();
//        String nl = ures.getNomLivre();
//        List <uReservation> listresna = ureservationRepository.findAllByNomLivreAndActifFalse(nl);
//        Date da = ures.getDateActivation();
//        long addtime = da.getTime() + 2 * 24 * 60 * 60 * 1000;
//        Date daPlus = new Date(addtime);
//        if (daPlus.before(new Date()) == true && listresna.size() == 0)
//        if(daPlus.before(new Date())){
//            ureservationRepository.delete(ures);}
//        if (daPlus.before(new Date()) == true && listresna.size() > 0){
//            uReservation uresPatch = ureservationRepository.findFirstByNomLivreAndActifIsFalseOrderByDateCreationAsc(nl);
//            uresPatch.setDateActivation(new Date());
//            uresPatch.setActif(true);
//            ureservationRepository.delete(ures);
//        }
//    }

    // Afficher la liste de uReservation d'un utilisateur
    @RequestMapping("/FileAttente/")
    public List<uReservation> fileAttente(@RequestParam(name = "nomUtil", defaultValue = "") String nomUtil) {
        List<uReservation> fileAttente = ureservationRepository.findAllByNomUtilisateurOrderByIdAsc(nomUtil);
        return fileAttente;
    }

    // Afficher la liste de uReservation par nomLivre
    @RequestMapping("/listResNomLivre/")
    public List<uReservation> listResNomLivre(@RequestParam(name = "nomLivre", defaultValue = "") String nomLivre) {
        List<uReservation> listeResNomLivre = ureservationRepository.findAllByNomLivre(nomLivre);
        return listeResNomLivre;
    }

    // Afficher une liste de uRes par nomlivre et non actif
    @RequestMapping("/listeUresByNLAndNA/")
    public List<uReservation> listResByNomLivreNotActiv(@RequestParam(name = "nomLivre", defaultValue = "") String nomLivre) {
        List<uReservation> listeResByNomLivreNoAct = ureservationRepository.findAllByNomLivreAndActifFalse(nomLivre);
        return listeResByNomLivreNoAct;
    }

    //Retourne booleen true si Ures active
    @RequestMapping("/isResActiv/")
    public boolean isResActiv(@RequestParam(name = "id", defaultValue = "") long id) {
        uReservation ures = ureservationRepository.getOne(id);
        boolean act = false;
        if (ures.isActif()) {
            act = true;
        }
        if (!ures.isActif()) {
            act = false;
        }
        return act;
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

    // Affiche une liste des reservation antérieur
    @GetMapping("/getListOlderRes/")
    public List<uReservation> listOlderRes(@RequestParam(name="nomLivre", defaultValue = "") String nomLivre,
                                           @RequestParam(name="id", defaultValue = "") long id){
        List <uReservation> listOlderRes = ureservationRepository.findAllByNomLivreAndIdIsLessThan(nomLivre, id);
        return listOlderRes;
    }

    // Obtenir la uReservation la plus ancienne d'un utilisateur
    @PatchMapping("/getOldUres/")
    uReservation ures (@RequestParam(name="nomLivre", defaultValue = "") String nomLivre,
                       @RequestParam(name="userMail", defaultValue = "") String userMail){
        uReservation ures = ureservationRepository.findFirstByNomLivreAndActifIsFalseOrderByDateCreationAsc(nomLivre);
        ures.setActif(true);
        ures.setDateActivation(new Date());
        ureservationRepository.save(ures);
//      Partie d'envoi des  mails
//        try {
//            SendEmail send = new SendEmail(userMail, "OcBiblio Reservation", "Votre réservation de l'ouvrage : ' " + nomLivre + " ' vous attends à la bibliothèque" +
//                    " Cordialement. L'équipe d'OcBiblio.");
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        return ures;
    }

    // Compte le nombre de uReservartion par utilisateur
    @RequestMapping("/CountuResByUser/")
    public int CountLoansForCount (@RequestParam(name = "nomUtilisateur", defaultValue = "") String nomUtilisateur) {
        List ListuResForCount = ureservationRepository.findAllByNomUtilisateur(nomUtilisateur);
        int countuRes = ListuResForCount.size();
        return countuRes;
    }

}
