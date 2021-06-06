package br.fumec.AAIBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.fumec.AAIBackend.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
