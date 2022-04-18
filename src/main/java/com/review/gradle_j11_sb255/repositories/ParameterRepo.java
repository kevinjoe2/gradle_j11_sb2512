package com.review.gradle_j11_sb255.repositories;

import com.review.gradle_j11_sb255.entities.ParameterEntity;
import com.review.gradle_j11_sb255.utils.emuns.ParameterCodeEnum;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface ParameterRepo extends R2dbcRepository<ParameterEntity, Long> {

    Mono<ParameterEntity> findByCode(ParameterCodeEnum code);

}
