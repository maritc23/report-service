package com.nttdata.controller;

import com.nttdata.model.Account;
import com.nttdata.model.Customer;
import com.nttdata.service.AccountService;
import com.nttdata.service.AccountServiceImpl;
import com.nttdata.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ReportController {
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
   
    
    @PostMapping("/get-account-report/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Map<String, Object>> getAccountsReport(@PathVariable("id") Integer id) {
    	logger.info("client report");
    	return clientService.findClient(id).flatMap(c->{
    		Integer clientId = c.getId();
    		String names = c.getName();
    		String lastNames = c.getLastname();
    		
    		Map<String, String> data = new HashMap<>();
    		data.put("id", clientId+"");
    		data.put("names", names);
    		data.put("lastNames", lastNames);
    		Mono<Map<String, String>> clientMono =  Mono.just(data);
    		
    		Mono<List<Account>> accountsMono =  accountService.findByCustomerId(clientId).collect(Collectors.toList());
    		return Mono.zip(clientMono, accountsMono, (client, accounts) -> {
    			Map<String, Object> retorno = new HashMap<>();
    			retorno.put("client", client);
    			retorno.put("accounts", accounts);
    			return retorno;
    		});
    		
    	});
     //   customerServiceImpl.createCustomer(employee);
    }


    
    

}
