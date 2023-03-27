package com.everis.everfuture.service;

import com.everis.everfuture.repo.EmpleadoRepoJPA;
import com.everis.everfuture.respository.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmpleadoServiceImpl implements EmpleadoService{

    @Autowired
    EmpleadoRepoJPA dao;

    @Override
    public Empleado insertar(Empleado e){
        return dao.save(e);
    }

    @Override
    public Empleado modificar(Empleado e) {
        return dao.save(e);
    }

    @Override
    public Empleado buscarPorUsername(String username) {
        return dao.findById(username).orElse(null);
    }

    @Override
    public void eliminar(Empleado e) {
        dao.delete(e);
    }

    @Override
    public List<Empleado> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public List<Empleado> buscarPorNombre(String nombre) {
        return dao.findByNombreLike(nombre);
    }
}
