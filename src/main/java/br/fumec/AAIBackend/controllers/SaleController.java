package br.fumec.AAIBackend.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.fumec.AAIBackend.dto.SaleDTO;
import br.fumec.AAIBackend.exceptions.NotFoundException;
import br.fumec.AAIBackend.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping
	public ResponseEntity<List<SaleDTO>> findAll() {
		List<SaleDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SaleDTO> findById(@PathVariable Long id) {
		try {
			SaleDTO sale = service.findById(id);
			
			return ResponseEntity.ok().body(sale);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO dto) throws NotFoundException {
		SaleDTO createdSale = service.createSale(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(createdSale);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SaleDTO> deleteSale(@PathVariable Long id) {
		try {
			service.deleteSaleById(id);
			return ResponseEntity.noContent().build();
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
