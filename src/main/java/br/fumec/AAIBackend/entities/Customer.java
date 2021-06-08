package br.fumec.AAIBackend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String cpf;
	private String email;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Sale> purchases = new ArrayList<>();
	
	public Customer() {
	}

	public Customer(Long id, String name, String cpf, String email) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
	}
	
	/*public Customer(CustomerDTO dto) {
		id = dto.getId();
		name = dto.getName();
		cpf = dto.getCpf();
		email = dto.getEmail();
		purchases = dto.getPurchases().stream();
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Sale> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Sale> purchases) {
		this.purchases = purchases;
	}
}
