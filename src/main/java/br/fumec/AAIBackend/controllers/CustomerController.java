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

import br.fumec.AAIBackend.dto.CustomerDTO;
import br.fumec.AAIBackend.services.CustomerService;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@GetMapping
	public ResponseEntity<List<CustomerDTO>> findAll() {
		List<CustomerDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> findById(@PathVariable Long id) {
		CustomerDTO customer = service.findById(id);
		return ResponseEntity.ok().body(customer);
	}

	@PostMapping
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO dto) {
		CustomerDTO createdCustomer = service.createCustomer(dto);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(dto.getId())
					.toUri();
		
		return ResponseEntity.created(uri).body(createdCustomer);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerDTO> editCustomer(@PathVariable Long id, @RequestBody CustomerDTO dto) {
		CustomerDTO customer = service.editCustomer(id, dto);
		return ResponseEntity.ok().body(customer);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable Long id) {
		service.deleteCustomerById(id);
		return ResponseEntity.noContent().build();
	}
}
