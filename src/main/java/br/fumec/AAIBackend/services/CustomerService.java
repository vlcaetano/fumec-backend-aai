package br.fumec.AAIBackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fumec.AAIBackend.dto.CustomerDTO;
import br.fumec.AAIBackend.entities.Customer;
import br.fumec.AAIBackend.exceptions.NotFoundException;
import br.fumec.AAIBackend.repositories.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;
	
	public List<CustomerDTO> findAll() {
		List<Customer> result = repository.findAll();
		return result.stream().map(customer -> new CustomerDTO(customer)).collect(Collectors.toList());
	}
	
	public CustomerDTO findById(Long id) throws NotFoundException {
		Customer customer = findCustomerById(id);
		
		return new CustomerDTO(customer);
	}
	
	public CustomerDTO createCustomer(CustomerDTO dto) {
		Customer customer = new Customer(null, dto.getName(), dto.getCpf(), dto.getEmail(), new ArrayList<>());
		Customer savedCustomer = repository.save(customer);
		return new CustomerDTO(savedCustomer);
	}
	
	public void deleteCustomerById(Long id) throws NotFoundException {
		Customer customer = findCustomerById(id);
		
		repository.delete(customer);
	}
	
	public CustomerDTO editCustomer(Long id, CustomerDTO dto) throws NotFoundException {
		Customer customer = findCustomerById(id);
		
		customer.setName(dto.getName());
		customer.setCpf(dto.getCpf());
		customer.setEmail(dto.getEmail());
		
		repository.save(customer);
		
		return new CustomerDTO(customer);
	}

	private Customer findCustomerById(Long id) throws NotFoundException {
		return repository.findById(id)
			.orElseThrow(() -> new NotFoundException("Cliente de id " + id + " não encontrado"));
	}
}
