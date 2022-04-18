package com.review.gradle_j11_sb255.services;

import com.review.gradle_j11_sb255.entities.ClientEntity;
import com.review.gradle_j11_sb255.entities.ParameterEntity;
import com.review.gradle_j11_sb255.exceptions.ConflictException;
import com.review.gradle_j11_sb255.mappers.ClientMapper;
import com.review.gradle_j11_sb255.repositories.ClientRepo;
import com.review.gradle_j11_sb255.repositories.ParameterRepo;
import com.review.gradle_j11_sb255.utils.emuns.ParameterCodeEnum;
import com.review.gradle_j11_sb255.utils.functions.ParameterFunction;
import com.review.gradle_j11_sb255.wrappers.ClientWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Log
@Service
@AllArgsConstructor
public class ClientService {

    final ClientRepo clientRepo;

    final ParameterRepo parameterRepo;

    final ClientMapper clientMapper;

    public Flux<ClientEntity> get(){

        return clientRepo.findAll();
    }

    public Flux<ClientEntity> getPersonWrap(){

        return clientRepo.findAll();
    }

    public Mono<ClientEntity> post(ClientWrapper clientWrap) {

        return assertClientNotExists(clientWrap.identification)

                .then(nextClientNumber(clientMapper.toEntity(clientWrap)))

                .flatMap(clientRepo::save);
    }

    public Flux<ClientEntity> postAll(List<ClientWrapper> clientWrapperList) {

        return Flux.fromStream(clientWrapperList.stream())

                .switchIfEmpty(Mono.error(new ConflictException("400", "Client is empty", HttpStatus.CONFLICT)))

                .flatMap(clientWrapper ->
                        assertClientNotExists(clientWrapper.identification)
                        .then(Mono.just(clientWrapper))
                )

                .flatMap(clientEntity -> nextClientNumber(clientMapper.toEntity(clientEntity)))

                .flatMap(clientRepo::save);
    }

    private Mono<Void> assertClientNotExists(String identification){

        return clientRepo.findByIdentification(identification)

                .flatMap(clientEntity -> Mono.error(
                    new ConflictException("400", "Client already exists: " + identification, HttpStatus.CONFLICT)
                ));
    }

    private Mono<ClientEntity> nextClientNumber(ClientEntity clientEntity){

        return parameterRepo.findByCode(ParameterCodeEnum.CLIENT_NUMBER)

                .switchIfEmpty(Mono.error(new ConflictException("400", "Parameter "+ ParameterCodeEnum.CLIENT_NUMBER.toString() +" not found", HttpStatus.CONFLICT)))

                .flatMap(parameterEntity -> {
                    parameterEntity.setValue(parameterEntity.getValue()+1);
                    return parameterRepo.save(parameterEntity);
                })

                .map(m -> {
                    clientEntity.setClientNumber(m.getPrefix() + m.getValue() + m.getSuffix());
                    return  clientEntity;
                });
    }

    private Mono<String> nextClientNumber(){

        return parameterRepo.findByCode(ParameterCodeEnum.CLIENT_NUMBER)

                .switchIfEmpty(Mono.error(new ConflictException("400", "Parameter "+ ParameterCodeEnum.CLIENT_NUMBER.toString() +" not found", HttpStatus.CONFLICT)))

                .flatMap(parameterEntity -> {
                    parameterEntity.setValue(parameterEntity.getValue()+1);
                    return parameterRepo.save(parameterEntity);
                })

                .map(m -> m.getPrefix() + m.getValue() + m.getSuffix());
    }

    public Mono<ClientEntity> put(Long id, ClientWrapper clientWrap){

        ClientEntity ClientEntity = clientMapper.toEntity(clientWrap);

        ClientEntity.setId(id);

        return clientRepo.save(ClientEntity);
    }

    public Mono<Void> delete(Long id){

        return clientRepo.deleteById(id);
    }

}
