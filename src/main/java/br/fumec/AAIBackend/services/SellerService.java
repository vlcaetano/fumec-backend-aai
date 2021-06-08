package br.fumec.AAIBackend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fumec.AAIBackend.dto.SellerDTO;
import br.fumec.AAIBackend.entities.Seller;
import br.fumec.AAIBackend.exceptions.EntityNotFoundException;
import br.fumec.AAIBackend.repositories.SellerRepository;

@Service
public class SellerService {
	@Autowired
	private SellerRepository repository;
	
	public List<SellerDTO> findAll() {
		List<Seller> result = repository.findAll();
		return result.stream().map(seller -> new SellerDTO(seller)).collect(Collectors.toList());
	}
	
	public SellerDTO findById(Long id) {
		Seller seller = findSellerById(id);
		
		return new SellerDTO(seller);
	}
	
	public SellerDTO createSeller(SellerDTO dto) {
		Seller seller = new Seller(null, dto.getName(), dto.getCpf(), dto.getEmail());
		Seller savedSeller = repository.save(seller);
		return new SellerDTO(savedSeller);
	}
	
	public void deleteSellerById(Long id) {
		Seller seller = findSellerById(id);
		
		repository.delete(seller);
	}
	
	public SellerDTO editSeller(Long id, SellerDTO dto) {
		Seller seller = findSellerById(id);
		
		seller.setName(dto.getName());
		seller.setCpf(dto.getCpf());
		seller.setEmail(dto.getEmail());
		
		repository.save(seller);
		
		return new SellerDTO(seller);
	}

	private Seller findSellerById(Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Vendedor de id " + id + " não encontrado"));
	}
}
