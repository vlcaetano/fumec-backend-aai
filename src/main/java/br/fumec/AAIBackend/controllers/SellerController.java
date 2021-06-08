package br.fumec.AAIBackend.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.fumec.AAIBackend.dto.SellerDTO;
import br.fumec.AAIBackend.exceptions.EntityNotFoundException;
import br.fumec.AAIBackend.services.SellerService;

@RestController
@RequestMapping(value = "/sellers")
public class SellerController {

	@Autowired
	private SellerService service;

	@GetMapping
	public ResponseEntity<List<SellerDTO>> findAll() {
		List<SellerDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SellerDTO> findById(@PathVariable Long id) {
		SellerDTO seller = service.findById(id);

		return ResponseEntity.ok().body(seller);
	}

	@PostMapping
	public ResponseEntity<SellerDTO> createSeller(@RequestBody SellerDTO dto) {
		SellerDTO createdSeller = service.createSeller(dto);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(dto.getId())
					.toUri();
		
		return ResponseEntity.created(uri).body(createdSeller);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SellerDTO> editSeller(@PathVariable Long id, @RequestBody SellerDTO dto) {
		try {
			SellerDTO seller = service.editSeller(id, dto);
			return ResponseEntity.ok().body(seller);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SellerDTO> deleteSeller(@PathVariable Long id) {
		service.deleteSellerById(id);
		return ResponseEntity.noContent().build();
	}
}
