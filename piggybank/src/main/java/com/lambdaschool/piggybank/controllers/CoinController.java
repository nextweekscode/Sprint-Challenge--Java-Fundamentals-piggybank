package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController {

    @Autowired
    private CoinRepository coinrepo;

    //http://localhost:2019/total
    @GetMapping(value = "/total", produces = {"application/json"})
    public ResponseEntity<?> listAllCoins() {
        List<Coin> coinList = new ArrayList<>();

        coinrepo.findAll().iterator().forEachRemaining(coinList::add);

        double total = 0;

        for (Coin c : coinList) {
            if (c.getQuantity() > 1 ) {
                System.out.println(c.getQuantity() + " " + c.getNameplural());
                total += c.getQuantity() * c.getValue();
            } else {
                System.out.println(c.getQuantity() + " " + c.getName());
                total += c.getValue();
            }
        }

        System.out.println("The piggy bank holds " + total);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}