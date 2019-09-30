package com.ocbibliov2.ocbibliobatch.proxies;

import com.ocbibliov2.ocbibliobatch.beans.BookBean;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "book-service", url = "localhost:8082")
public interface MicroserviceBookProxy {

    @RequestMapping("/allBooks")
    public List<BookBean> allBooks ();
}
