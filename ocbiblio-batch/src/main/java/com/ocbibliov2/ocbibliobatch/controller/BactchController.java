package com.ocbibliov2.ocbibliobatch.controller;

import com.ocbibliov2.ocbibliobatch.beans.BookBean;
import com.ocbibliov2.ocbibliobatch.proxies.MicroserviceBookProxy;
import com.ocbibliov2.ocbibliobatch.proxies.MicroserviceuReservationProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BactchController {

    @Autowired
    private MicroserviceBookProxy bookProxy;

    @Autowired
    private MicroserviceuReservationProxy uReservationProxy;

    @RequestMapping("/allBooks")
    public List<BookBean> allBooks () {
        return bookProxy.allBooks();
    }

    @GetMapping("/batchMethod/")
    public void deleteResBatch(){
        uReservationProxy.deleteResBatch();
    }

}
