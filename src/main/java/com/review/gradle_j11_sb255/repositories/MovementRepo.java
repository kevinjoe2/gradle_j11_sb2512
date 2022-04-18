package com.review.gradle_j11_sb255.repositories;

import com.review.gradle_j11_sb255.entities.MovementEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepo extends R2dbcRepository<MovementEntity,Long> {
}
