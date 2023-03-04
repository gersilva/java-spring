package com.platzi.market.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platzi.market.domain.Purchase;
import com.platzi.market.domain.service.PurchaseService;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	
	@GetMapping("/all")
	private ResponseEntity<List<Purchase>> getAll(){
		return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{clientId}")
	private ResponseEntity<List<Purchase>> getByClient(@PathVariable("clientId") String clientId){
		return purchaseService.getByClient(clientId)
				.map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/save")
	private ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
		return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
	}
	

}
