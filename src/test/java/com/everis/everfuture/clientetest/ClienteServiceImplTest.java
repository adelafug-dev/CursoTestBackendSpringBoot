package com.everis.everfuture.clientetest;

import com.everis.everfuture.ClienteRepoJPA;
import com.everis.everfuture.respository.entity.Cliente;
import com.everis.everfuture.service.ClienteService;
import com.everis.everfuture.service.ClienteServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Tag("bbdd_h2")
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@TestMethodOrder(MethodOrderer.class)
class ClienteServiceImplTest {

    public static final String dni = "77845530Y";
    public static final String dni2 = "77845535Y";
    public static final String dni3 = "77845550Y";

    @Autowired
    ClienteRepoJPA dao;

    @Autowired
    ClienteService clienteService;

    @Test
    @Order(1)
    void insertar() {
        // GIVE:
        Optional<Cliente> oc = dao.findById(dni);
        assertFalse(oc.isPresent(), "No existe el Cliente con DNI : " + oc);

        // WHEN:
        Cliente c = new Cliente(dni, "nomb", "apell", "direcc");
        c = clienteService.insertar(c);

        // THEN:
        oc = dao.findById(dni);
        assertTrue(oc.isPresent(), "Si existe el cliente con DNI: " + oc);

        dao.delete(c);

    }

    @Test
    @Order(2)
    void modificar() {
        // GIVE:
        String nuevoNombre = "NuevoNombre";
        Cliente c = new Cliente(dni, "n", "a", "d");
        c = dao.save(c);
        Optional<Cliente> oc = dao.findById(dni);
        assertFalse(oc.isPresent(), "No existe el Cliente con DNI : " + oc);

        // WHEN:
        c.setName(nuevoNombre);
        c = clienteService.modificar(c);

        // THEN:
        oc = dao.findById(dni);
        assertTrue(oc.isPresent(), "Si existe el cliente con DNI: " + oc);
        assertEquals(nuevoNombre, oc.get().getName(), "Debe tener el mismo nombre");

        dao.delete(c);
    }

    @Test
    @Order(3)
    void eliminar() throws Exception{
        // GIVEN:
        Cliente c = new Cliente(dni, "n", "a", "d");
        c = dao.save(c);
        Optional<Cliente> oc = dao.findById(dni);
        assertFalse(oc.isPresent(), "No existe el Cliente con DNI : " + oc);

        // WHEN:
        clienteService.eliminar(c);
        c = clienteService.modificar(c);

        // THEN:
        oc = dao.findById(dni);
        assertFalse(oc.isPresent(), "Ya no existe el cliente con DNI: " + oc);
    }

    @Test
    @Order(4)
    void buscarPorDNI() throws Exception{
        // GIVEN:
        Cliente c1 = new Cliente(dni, "Ana", "a", "d");
        Cliente c2 = new Cliente(dni2, "Lucas", "abb", "ad");
        c1 = dao.save(c1);
        c2 = dao.save(c2);

        // WHEN:
        Cliente resultado = clienteService.buscarPorDNI(dni);
        Cliente resultado2 = clienteService.buscarPorDNI("");

        // THEN:
        assertNotNull(resultado, "Encuentra cliente en BBDD");
        assertEquals(dni, resultado.getDni(),"Tiene el DNI que hemos buscado");
        assertNull(resultado2);

        dao.delete(c1);
        dao.delete(c2);
    }

    @Test
    @Order(5)
    void buscarTodos() throws Exception{
        // GIVEN:
        Cliente c1 = new Cliente(dni, "Ana", "a", "d");
        Cliente c2 = new Cliente(dni2, "Lucas", "abb", "ad");
        Cliente c3 = new Cliente(dni2, "Tomas", "abcb", "aad");
        c1 = dao.save(c1);
        c2 = dao.save(c2);
        c3 = dao.save(c3);
        List<Cliente> clientesBBDD = dao.findAll();
        assertEquals(3, clientesBBDD.size(), "Hay tres clientes en BBDD");

        // WHEN:
        List<Cliente> resultado = clienteService.buscarTodos();

        // THEN:
        assertEquals(3, resultado.size(), "Deben existir 3 clientes en BBDD");

        dao.delete(c1);
        dao.delete(c2);
        dao.delete(c3);
    }

    @Test
    @Order(6)
    void buscarPorNombre() throws Exception{
        // GIVEN:
        String ana = "Ana";
        Cliente c1 = new Cliente(dni, ana, "a", "d");
        Cliente c2 = new Cliente(dni2, ana, "abb", "ad");
        Cliente c3 = new Cliente(dni2, "Tomas", "abcb", "aad");
        c1 = dao.save(c1);
        c2 = dao.save(c2);
        c3 = dao.save(c3);
        List<Cliente> clientesBBDD = dao.findAll();
        assertEquals(3, clientesBBDD.size(), "Hay tres clientes en BBDD");

        // WHEN:
        List<Cliente> resultado = clienteService.buscarPorNombre(ana);

        // THEN:
        assertEquals(2, resultado.size(), "Deben existir 2 clientes en BBDD que se llamen Ana");

        dao.delete(c1);
        dao.delete(c2);
        dao.delete(c3);
    }

}