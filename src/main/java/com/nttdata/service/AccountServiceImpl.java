package com.nttdata.service;

import com.nttdata.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@Service
public class AccountServiceImpl implements AccountService {
	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	private final WebClient webclient;
	
	public AccountServiceImpl() {
		webclient = WebClient.create("http://localhost:8083/");
	}
	
	@Override
	public Flux<Account> findByCustomerId(Integer customerId) {
		logger.info("findByCustomerId "+customerId);
		return  webclient.get()
                .uri("/accounts-by-client/{clientId}",customerId)
                .retrieve()
                .bodyToFlux(Account.class);
	}

    

    
	
}