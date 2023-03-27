package com.everis.everfuture.clientetest;

import com.everis.everfuture.ClienteRepoJPA;
import com.everis.everfuture.respository.entity.Cliente;
import com.everis.everfuture.respository.entity.Empleado;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("bbdd_h2")
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class ClienteRepoJPATest {

    @Autowired
    ClienteRepoJPA dao;

    @Test
    void Test(){
        // GIVE:
        String dnitest = "77845530Y";
        Optional<Cliente> oc = dao.findById(dnitest);
        assertFalse(oc.isPresent());

        // WHEN:
        Cliente c = new Cliente(dnitest, "nomb", "apell", "direcc");
        c = dao.save(c);

        // THEN:
        Optional<Cliente> oc2 = dao.findById(dnitest);
        assertTrue(oc2.isPresent());

        // Volver al estado inicial
        dao.delete(c);

    }

}
