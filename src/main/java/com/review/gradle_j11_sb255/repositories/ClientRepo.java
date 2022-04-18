package com.review.gradle_j11_sb255.repositories;

import com.review.gradle_j11_sb255.entities.ClientEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ClientRepo extends R2dbcRepository<ClientEntity,Long> {

    Mono<ClientEntity> findByIdentification(String identification);

}
