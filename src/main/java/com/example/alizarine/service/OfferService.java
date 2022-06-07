package com.example.alizarine.service;

import com.example.alizarine.domain.ObjectCategory;
import com.example.alizarine.domain.Offer;
import com.example.alizarine.domain.User;
import com.example.alizarine.domain.UserDTO;
import com.example.alizarine.repository.ObjectCategoryRepository;
import com.example.alizarine.repository.OfferRepository;
import com.example.alizarine.repository.UserRepository;
import com.sun.istack.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;
    private final ObjectCategoryRepository objectCategoryRepository;
    private final UserRepository userRepository;

    public ResponseEntity<Offer> createOffer(Offer requestedOffer) {
        Offer offer = new Offer();
        return createOffer(requestedOffer, offer);
    }

    public ResponseEntity<String> updateOffer(Offer requestedOffer) {
        Optional<Offer> offer = offerRepository.findById(requestedOffer.getId());
        if (offer.isPresent()) {
            Offer updatedOffer = offer.get();
            updateOfferFields(requestedOffer, updatedOffer);
            log.debug("Offer updated : {}", updatedOffer);
            return ResponseEntity.ok().body("Offer updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Offer> getOffer(@Nullable Long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        return ResponseEntity.of(offer);
    }

    public ResponseEntity<ArrayList<Offer>> getOffers() {
        ArrayList<Offer> offers = new ArrayList<>(offerRepository.findAll());
        return ResponseEntity.ok(offers);
    }

    public ResponseEntity<String> deleteOffer(Long id) {
        Optional<Offer> requestedOffer = offerRepository.findById(id);
        if (requestedOffer.isPresent()) {
            offerRepository.delete(requestedOffer.get());
            log.debug("Offer deleted succesfully : {}", requestedOffer);
            return ResponseEntity.ok("Offer deleted successfully");
        } else { return ResponseEntity.notFound().build(); }
    }

    private ResponseEntity<Offer> createOffer(Offer requestedOffer, Offer offer) {
        offer.setTitle(requestedOffer.getTitle());
        offer.setDescription(requestedOffer.getDescription());
        offer.setObjectCategory(requestedOffer.getObjectCategory());
        offer.setPrice(requestedOffer.getPrice());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Optional<User> user = userRepository.findById(userDetails.getId());
        if (user.isPresent()) {
            offer.setSeller(user.get());
        } else { return ResponseEntity.notFound().build(); }
        offer.setPostDate(Instant.now());
        offer.setStatus("selling");
        offerRepository.saveAndFlush(offer);
        log.debug("New offer created: {}", offer);
        return ResponseEntity.ok(offer);
    }

    private void updateOfferFields(Offer requestedOffer, Offer offer) {
        if (requestedOffer.getTitle() != null) { offer.setTitle(requestedOffer.getTitle()); }
        if (requestedOffer.getDescription() != null) { offer.setDescription(requestedOffer.getDescription()); }
        if (requestedOffer.getObjectCategory() != null) {
            Optional<ObjectCategory> objectCategory = objectCategoryRepository.findById(requestedOffer.getObjectCategory().getId());
            objectCategory.ifPresent(offer::setObjectCategory);
            offer.setObjectCategory(requestedOffer.getObjectCategory());
        }
        if (requestedOffer.getPrice() != null) { offer.setPrice(requestedOffer.getPrice()); }
        if (requestedOffer.getPostDate() != null) { offer.setPostDate(Instant.now()); }
        if (requestedOffer.getStatus() != null) { offer.setStatus(requestedOffer.getStatus()); }
        offerRepository.saveAndFlush(offer);
    }
}
