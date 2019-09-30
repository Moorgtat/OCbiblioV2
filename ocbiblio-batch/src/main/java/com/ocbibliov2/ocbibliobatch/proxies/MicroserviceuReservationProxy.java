package com.ocbibliov2.ocbibliobatch.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "reservation-service", url = "localhost:8084")
public interface MicroserviceuReservationProxy {

    @GetMapping("/batchMethod/")
    public void deleteResBatch();
}
