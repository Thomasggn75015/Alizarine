package com.example.alizarine.service;

import com.example.alizarine.domain.ObjectCategory;
import com.example.alizarine.repository.ObjectCategoryRepository;
import com.sun.istack.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ObjectCategoryService {
    private final ObjectCategoryRepository objectCategoryRepository;

    public ResponseEntity<ObjectCategory> createObjectCategory(ObjectCategory requestedObjectCategory) {
        ObjectCategory objectCategory = new ObjectCategory();
        return createObjectCategory(requestedObjectCategory, objectCategory);
    }

    public ResponseEntity<String> updateObjectCategory(ObjectCategory requestedObjectCategory) {
        Optional<ObjectCategory> objectCategory = objectCategoryRepository.findById(requestedObjectCategory.getId());
        if (objectCategory.isPresent()) {
            ObjectCategory updatedObjectCategory = objectCategory.get();
            updateObjectCategoryFields(requestedObjectCategory, updatedObjectCategory);
            log.debug("ObjectCategory updated : {}", updatedObjectCategory);
            return ResponseEntity.ok().body("ObjectCategory updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<ObjectCategory> getObjectCategory(Long id) {
        Optional<ObjectCategory> objectCategory = objectCategoryRepository.findById(id);
        return ResponseEntity.of(objectCategory);
    }

    public ResponseEntity<ArrayList<ObjectCategory>> getObjectCategories() {
        ArrayList<ObjectCategory> objectCategories = new ArrayList<>(objectCategoryRepository.findAll());
        return ResponseEntity.ok(objectCategories);
    }

    public ResponseEntity<String> deleteObjectCategory(Long id) {
        Optional<ObjectCategory> requestedObjectCategory = objectCategoryRepository.findById(id);
        if (requestedObjectCategory.isPresent()) {
            objectCategoryRepository.delete(requestedObjectCategory.get());
            log.debug("ObjectCategory deleted succesfully : {}", requestedObjectCategory);
            return ResponseEntity.ok("ObjectCategory deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<ObjectCategory> createObjectCategory(ObjectCategory requestedObjectCategory, ObjectCategory objectCategory) {
        objectCategory.setName(requestedObjectCategory.getName());
        objectCategory.setMaxPrice(requestedObjectCategory.getMaxPrice());
        objectCategoryRepository.saveAndFlush(objectCategory);
        log.debug("New objectCategory created: {}", objectCategory);
        return ResponseEntity.ok(objectCategory);
    }

    private void updateObjectCategoryFields(ObjectCategory requestedObjectCategory, ObjectCategory objectCategory) {
        if (requestedObjectCategory.getName() != null) { objectCategory.setName(requestedObjectCategory.getName()); }
        if (requestedObjectCategory.getMaxPrice() != null  ) { objectCategory.setMaxPrice(requestedObjectCategory.getMaxPrice()); }
        objectCategoryRepository.saveAndFlush(objectCategory);
    }
}
