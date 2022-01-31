package com.nttdata.service;

import com.nttdata.model.Account;

import reactor.core.publisher.Flux;
 
public interface AccountService
{
     
    Flux<Account> findByCustomerId(Integer customerId);
 
    //Mono<Account> updateCustomer(Account e);
 
   // Mono<Void> deleteCustomer(Integer id);
}