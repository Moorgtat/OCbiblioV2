package org.ocbiblio.reservationservice;

import org.ocbiblio.reservationservice.dao.ReservationRepository;
import org.ocbiblio.reservationservice.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ReservationServiceApplication implements CommandLineRunner{

//    @Autowired
//    private ReservationRepository reservationRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Reservation r1 = reservationRepository.save(new Reservation("Kafka sur le rivage", true, 2));
//        Reservation r2 = reservationRepository.save(new Reservation("Le bûcher des vanités", true, 1));
//        Reservation r3 = reservationRepository.save(new Reservation("Le Monde d'hier", true, 2));
//        Reservation r4 = reservationRepository.save(new Reservation("L'épopée du buveur d'eau", true, 2));
//        Reservation r5 = reservationRepository.save(new Reservation("L'attrape Coeur", true, 2));
//        Reservation r6 = reservationRepository.save(new Reservation("Le grand voyage", true, 1));
//        Reservation r7 = reservationRepository.save(new Reservation("Le Parfum", true, 1));
//        Reservation r8 = reservationRepository.save(new Reservation("Un bonheur insoutenable", true, 1));
//        Reservation r9 = reservationRepository.save(new Reservation("Le cycle de Tschaï", true, 2));
//        Reservation r10 = reservationRepository.save(new Reservation("Le dahlia noir", true, 2));
//        Reservation r11 = reservationRepository.save(new Reservation("L'été Diabolik", true, 2));
    }
}
