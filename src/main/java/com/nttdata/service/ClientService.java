package com.nttdata.service;

import com.nttdata.model.Customer;

import reactor.core.publisher.Mono;

public interface ClientService {
	Mono<Customer> findClient(Integer clientId); 
}
