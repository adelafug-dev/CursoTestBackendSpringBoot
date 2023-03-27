package com.everis.everfuture.empleadotest;

import com.everis.everfuture.respository.entity.Empleado;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Normal")
public class EmpleadoTest {

    @Test
    void test(){
        // GIVEN:
            String username = "testUsername";
            String nombre = "Nombre1";
            String appelido = "Apellidos 1 y 2";
            long salario = 1800;

        // WHEN:
            Empleado e1 = new Empleado();
            e1.setUsername(username);
            e1.setNombre(nombre);
            e1.setApellidos(appelido);
            e1.setSalario(salario);
            Empleado e2 = new Empleado(username, "n", "a", 1000);
            
        // THEN:
            // Para que corra todos los fallos podemos usar assertAll()
            assertEquals(username, e1.getUsername(), "Mismo Username");
            assertEquals(nombre, e1.getNombre(), "Mismo nombre");
            assertEquals(appelido, e1.getApellidos(), "Mismo apellido");
            assertEquals(salario, e1.getSalario(), "Mismo salario");
            assertEquals(e1, e1);
            assertEquals(e1, e2);
            assertEquals(Objects.hash(e1.getUsername()), e1.hashCode());
    }

}
