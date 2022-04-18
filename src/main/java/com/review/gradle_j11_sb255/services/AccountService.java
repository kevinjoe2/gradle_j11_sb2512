package com.review.gradle_j11_sb255.services;

import com.review.gradle_j11_sb255.entities.AccountEntity;
import com.review.gradle_j11_sb255.mappers.AccountMapper;
import com.review.gradle_j11_sb255.repositories.AccountRepo;
import com.review.gradle_j11_sb255.wrappers.AccountWrap;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AccountService {

    final AccountRepo accountRepo;

    final AccountMapper accountMapper;

    public Flux<AccountEntity> get(){

        return accountRepo.findAll();
    }

    public Mono<AccountEntity> post(AccountWrap accountWrap){

        AccountEntity accountEntity = accountMapper.accountWrapToAccountEntity(accountWrap);

        return accountRepo.save(accountEntity);
    }

    public Mono<AccountEntity> put(Long id, AccountWrap accountWrap){

        AccountEntity accountEntity = accountMapper.accountWrapToAccountEntity(accountWrap);

        accountEntity.setId(id);

        return accountRepo.save(accountEntity);
    }

    public Mono<Void> delete(Long id){

        return accountRepo.deleteById(id);
    }

}
