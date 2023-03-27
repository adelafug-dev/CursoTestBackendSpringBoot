package com.everis.everfuture;

import com.everis.everfuture.respository.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ClienteRepoJPA extends JpaRepository<Cliente, String> {
    List<Cliente> findByNombreLike(String nombre);
}
