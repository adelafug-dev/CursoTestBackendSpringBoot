package com.everis.everfuture.service;

import com.everis.everfuture.ClienteRepoJPA;
import com.everis.everfuture.respository.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    ClienteRepoJPA dao;

    @Override
    public Cliente insertar(Cliente c) {
        return dao.save(c);
    }

    @Override
    public Cliente modificar(Cliente c) {
        return dao.save(c);
    }

    @Override
    public void eliminar(Cliente c) throws Exception {
        dao.delete(c);
    }

    @Override
    public Cliente buscarPorDNI(String dni) throws Exception {
        return dao.findById(dni).orElse(null);
    }

    @Override
    public List<Cliente> buscarTodos() throws Exception {
        return dao.findAll();
    }

    @Override
    public List<Cliente> buscarPorNombre(String s) throws Exception {
        return dao.findByNombreLike(s);
    }
}
