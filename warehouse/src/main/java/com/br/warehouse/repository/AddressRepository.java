package com.br.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.warehouse.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long>{

}
