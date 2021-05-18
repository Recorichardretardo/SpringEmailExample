package com.example.emailexample;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController 
{
    @GetMapping(path="/testEmailSend", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product  homeInit() {
        return  new Product(1, "iPhone", 999.99f);
    }
}
	