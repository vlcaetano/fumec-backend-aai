package br.fumec.AAIBackend.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class SaleDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Double amount;
	private LocalDate date;
	
	private SellerDTO sellerDTO;
	private CustomerDTO customerDTO;

	public SaleDTO() {
	}

	public SaleDTO(Long id, Double amount, LocalDate date, SellerDTO sellerDTO, CustomerDTO customerDTO) {
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.sellerDTO = sellerDTO;
		this.customerDTO = customerDTO;
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

	public SellerDTO getSellerDTO() {
		return sellerDTO;
	}

	public void setSellerDTO(SellerDTO sellerDTO) {
		this.sellerDTO = sellerDTO;
	}

	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}

	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
