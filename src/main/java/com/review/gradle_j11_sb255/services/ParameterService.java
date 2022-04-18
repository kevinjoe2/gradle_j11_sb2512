package com.review.gradle_j11_sb255.services;

import com.review.gradle_j11_sb255.entities.ParameterEntity;
import com.review.gradle_j11_sb255.mappers.ParameterMapper;
import com.review.gradle_j11_sb255.repositories.ParameterRepo;
import com.review.gradle_j11_sb255.wrappers.ParameterWrap;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ParameterService {

    private ParameterRepo parameterRepo;

    private final ParameterMapper parameterMapper = Mappers.getMapper(ParameterMapper.class);

    public Flux<ParameterEntity> get(){

        return parameterRepo.findAll();
    }

    public Mono<ParameterEntity> post(ParameterWrap parameterWrap){

        ParameterEntity ParameterEntity = parameterMapper.parameterWrapToParameterEntity(parameterWrap);

        return parameterRepo.save(ParameterEntity);
    }

    public Mono<ParameterEntity> put(Long id, ParameterWrap parameterWrap){

        ParameterEntity ParameterEntity = parameterMapper.parameterWrapToParameterEntity(parameterWrap);

        ParameterEntity.setId(id);

        return parameterRepo.save(ParameterEntity);
    }

    public Mono<Void> delete(Long id){

        return parameterRepo.deleteById(id);
    }

}
