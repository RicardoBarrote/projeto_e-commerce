package com.fbr.ecommerce.repository;

import com.fbr.ecommerce.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
