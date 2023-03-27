package com.everis.everfuture.repo;

import com.everis.everfuture.respository.entity.Cliente;
import com.everis.everfuture.respository.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// extends JpaRepository<Empleado, String> se pone empleado porque es la clase que usaremos para el repo y el string porque está identificado con el @Id
@Repository
public interface EmpleadoRepoJPA extends JpaRepository<Empleado, String> {

    List<Empleado> findByNombreLike(String nombre);

}
