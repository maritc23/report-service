package com.nttdata.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.controller.ReportController;
import com.nttdata.exception.NttException;
import com.nttdata.model.Account;
import com.nttdata.model.Customer;

import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService{
	
	
	private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
	private final WebClient webclient;

	
	public ClientServiceImpl() {
		//this.webclient = webclient;
		webclient = WebClient.create("http://localhost:8081/");
		//this.webClient = webClientBuilder.baseUrl("http://localhost:8081/").build();
	}
	
	public Mono<Customer> findClient(Integer clientId) { 
		return  webclient.get()
                .uri("/customers/{id}",clientId)
                .retrieve()
                .bodyToMono(Customer.class);
    }
	
	

	
}	
