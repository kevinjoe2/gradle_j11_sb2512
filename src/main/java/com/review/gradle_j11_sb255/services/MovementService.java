package com.review.gradle_j11_sb255.services;

import com.review.gradle_j11_sb255.entities.MovementEntity;
import com.review.gradle_j11_sb255.mappers.MovementMapper;
import com.review.gradle_j11_sb255.repositories.MovementRepo;
import com.review.gradle_j11_sb255.wrappers.MovementWrap;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class MovementService {

    private MovementRepo movementRepo;

    private final MovementMapper movementMapper = Mappers.getMapper(MovementMapper.class);

    public Flux<MovementEntity> get(){

        return movementRepo.findAll();
    }

    public Mono<MovementEntity> post(MovementWrap movementWrap){

        MovementEntity MovementEntity = movementMapper.movementWrapToMovementEntity(movementWrap);

        return movementRepo.save(MovementEntity);
    }

    public Mono<MovementEntity> put(Long id, MovementWrap movementWrap){

        MovementEntity MovementEntity = movementMapper.movementWrapToMovementEntity(movementWrap);

        MovementEntity.setId(id);

        return movementRepo.save(MovementEntity);
    }

    public Mono<Void> delete(Long id){

        return movementRepo.deleteById(id);
    }

}
