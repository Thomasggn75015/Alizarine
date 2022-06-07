package com.example.alizarine.controller;

import com.example.alizarine.domain.ObjectCategory;
import com.example.alizarine.service.ObjectCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api/objectCategory")
@RequiredArgsConstructor
@CrossOrigin
public class ObjectCategoryController {
    private final ObjectCategoryService objectCategoryService;

    @PostMapping("")
    public ResponseEntity<ObjectCategory> createObjectCategory(@Valid @RequestBody ObjectCategory objectCategory) {
        log.debug("REST request to save objectCategory : {}", objectCategory);
        return objectCategoryService.createObjectCategory(objectCategory);
    }

    @PutMapping("")
    public ResponseEntity<?> updateObjectCategory(@Valid @RequestBody ObjectCategory objectCategory) {
        log.debug("REST request to update objectCategory : {}", objectCategory);
        return objectCategoryService.updateObjectCategory(objectCategory);
    }

    @GetMapping("/{objectCategoryId}")
    public ResponseEntity<ObjectCategory> getObjectCategory(@Valid @PathVariable Long objectCategoryId) {
        log.debug("REST request to find objectCategory with id : {}", objectCategoryId);
        return objectCategoryService.getObjectCategory(objectCategoryId);

    }

    @GetMapping("")
    public ResponseEntity<ArrayList<ObjectCategory>> getObjectCategories() {
        log.debug("REST request to find all objectCategories");
        return objectCategoryService.getObjectCategories();
    }

    @DeleteMapping("/{objectCategoryId}")
    public ResponseEntity<?> deleteObjectCategory(@Valid @PathVariable Long objectCategoryId) {
        log.debug("REST request to delete objectCategory with id : {}", objectCategoryId);
        return objectCategoryService.deleteObjectCategory(objectCategoryId);
    }
}
