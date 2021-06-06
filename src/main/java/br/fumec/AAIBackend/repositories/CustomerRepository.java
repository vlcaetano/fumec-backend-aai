package br.fumec.AAIBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.fumec.AAIBackend.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
