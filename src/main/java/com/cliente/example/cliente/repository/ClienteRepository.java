package com.cliente.example.cliente.repository;

import com.cliente.example.cliente.dominio.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{


}

