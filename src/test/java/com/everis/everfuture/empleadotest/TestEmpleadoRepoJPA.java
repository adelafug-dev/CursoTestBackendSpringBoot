package com.everis.everfuture.empleadotest;

import com.everis.everfuture.repo.EmpleadoRepoJPA;
import com.everis.everfuture.respository.entity.Empleado;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@Tag("mockeado")
@ExtendWith(MockitoExtension.class)
public class TestEmpleadoRepoJPA {

    @Mock
    EmpleadoRepoJPA daoMock;

    @Test
    void test(){

        //GIVEN:
            String username = "testUser";
            Empleado e = new Empleado(username,"nombre","ape", 1500);
            // El optional se usa para que cuando vaya a devolver algo vacio devuelva el Optional
            Optional<Empleado> ope =  Optional.of(e);

        // WHEN:
            when( daoMock.findById(username) ).thenReturn(ope);
            Empleado rdo1 = daoMock.findById(username).orElse(null);
            Empleado rdo2 = daoMock.findById("").orElse(null);

        // THEN:
            assertEquals(e, rdo1);
            assertNull(rdo2);

    }

}
