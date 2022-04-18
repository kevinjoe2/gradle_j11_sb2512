package com.review.gradle_j11_sb255.repositories;

import com.review.gradle_j11_sb255.entities.LeadEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepo extends R2dbcRepository<LeadEntity,Long> {
}
