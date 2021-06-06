package br.fumec.AAIBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.fumec.AAIBackend.entities.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {

}
