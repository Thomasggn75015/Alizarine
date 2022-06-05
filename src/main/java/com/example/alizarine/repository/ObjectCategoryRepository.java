package com.example.alizarine.repository;

import com.example.alizarine.domain.ObjectCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectCategoryRepository extends JpaRepository<ObjectCategory, Long> {}
