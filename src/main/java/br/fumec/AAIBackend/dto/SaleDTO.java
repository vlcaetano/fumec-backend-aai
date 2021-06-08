package br.fumec.AAIBackend.dto;

import java.io.Serializable;
import java.time.LocalDate;

import br.fumec.AAIBackend.entities.Sale;

public class SaleDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Double amount;
	private LocalDate date;
	
	private SellerDTO seller;
	private CustomerDTO customer;

	public SaleDTO() {
	}
	
	public SaleDTO(Sale entity) {
		id = entity.getId();
		amount = entity.getAmount();
		date = entity.getDate();
		seller = new SellerDTO(entity.getSeller());
		customer = new CustomerDTO(entity.getCustomer());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public SellerDTO getSeller() {
		return seller;
	}

	public void setSeller(SellerDTO seller) {
		this.seller = seller;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
}
