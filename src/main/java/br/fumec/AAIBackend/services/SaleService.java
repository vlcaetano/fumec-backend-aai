package br.fumec.AAIBackend.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fumec.AAIBackend.dto.CustomerDTO;
import br.fumec.AAIBackend.dto.SaleDTO;
import br.fumec.AAIBackend.dto.SellerDTO;
import br.fumec.AAIBackend.entities.Customer;
import br.fumec.AAIBackend.entities.Sale;
import br.fumec.AAIBackend.entities.Seller;
import br.fumec.AAIBackend.exceptions.EntityNotFoundException;
import br.fumec.AAIBackend.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SellerService sellerService;
	
	public List<SaleDTO> findAll() {
		List<Sale> result = repository.findAll();
		return result.stream().map(sale -> new SaleDTO(sale)).collect(Collectors.toList());
	}
	
	public SaleDTO findById(Long id) {
		Sale sale = findSaleById(id);
		
		return new SaleDTO(sale);
	}
	
	public SaleDTO createSale(SaleDTO dto) {
		CustomerDTO customerDTO = customerService.findById(dto.getCustomer().getId());
		Customer customer = new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getCpf(), customerDTO.getEmail());
		
		SellerDTO sellerDTO = sellerService.findById(dto.getSeller().getId());
		Seller seller = new Seller(sellerDTO.getId(), sellerDTO.getName(), sellerDTO.getCpf(), sellerDTO.getEmail());
		
		Sale sale = new Sale(null, dto.getAmount(), LocalDate.now(), seller, customer);
		
		Sale savedSale = repository.save(sale);
		return new SaleDTO(savedSale);
	}
	
	public void deleteSaleById(Long id) {
		Sale sale = findSaleById(id);
		
		repository.delete(sale);
	}

	private Sale findSaleById(Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Venda de id " + id + " não encontrada"));
	}
}
