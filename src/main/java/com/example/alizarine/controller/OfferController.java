package com.example.alizarine.controller;

import com.example.alizarine.domain.Offer;
import com.example.alizarine.service.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api/offer")
@RequiredArgsConstructor
@CrossOrigin
public class OfferController {
    private final OfferService offerService;

    @PostMapping("")
    public ResponseEntity<Offer> createOffer(@Valid @RequestBody Offer offer) {
        log.debug("REST request to save offer : {}", offer);
        return offerService.createOffer(offer);
    }

    @PutMapping("")
    public ResponseEntity<?> updateOffer(@Valid @RequestBody Offer offer) {
        log.debug("REST request to update offer : {}", offer);
        return offerService.updateOffer(offer);
    }

    @GetMapping("/{offerId}")
    public ResponseEntity<Offer> getOffer(@Valid @PathVariable Long offerId) {
        log.debug("REST request to find offer with id : {}", offerId);
        return offerService.getOffer(offerId);

    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Offer>> getOffers() {
        log.debug("REST request to find all objectCategories");
        return offerService.getOffers();
    }

    @DeleteMapping("/{offerId}")
    public ResponseEntity<?> deleteOffer(@Valid @PathVariable Long offerId) {
        log.debug("REST request to delete offer with id : {}", offerId);
        return offerService.deleteOffer(offerId);
    }
}
