package com.everis.everfuture.empleadotest;

import com.everis.everfuture.controller.EmpleadoRestController;
import com.everis.everfuture.repo.EmpleadoRepoJPA;
import com.everis.everfuture.respository.entity.Empleado;
import com.everis.everfuture.service.EmpleadoService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@Tag("mockeado")
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.class)
public class EmpleadoControllerTest {

    private static final String username = "userTest";

    @Mock
    EmpleadoRepoJPA daoMock;

    @Mock
    EmpleadoService serviceMock;

    @InjectMocks
    EmpleadoRestController controllerMock;

    @Test
    @Order(1)
    void insertartTest() throws Exception{
        // GIVEN:
        Empleado e1 = new Empleado(username, "nombre", "apel",0);
        Empleado e2 = new Empleado(username, "nombre", "apel",2000);

        // WHEN:
        when(serviceMock.insertar(e1)).thenReturn(e2);
        ResponseEntity<Empleado> ree = controllerMock.insertar(e1);

        // THEN:
        assertAll(
                ()-> assertEquals(HttpStatus.CREATED, ree.getStatusCode(), "Código devuelto"),
                () -> assertEquals(e2, ree.getBody(), "Mismo Empleado"),
                () -> assertEquals(2000, ree.getBody().getSalario(), "Nuevo Salario")
        );
    }

    @Test
    @Order(2)
    void insertarTestExeption() throws Exception{
        // GIVEN:
        Empleado e1 = new Empleado(username, "nombre", "apel",0);

        // WHEN:
        doThrow(new Exception()).when(serviceMock).insertar(e1);
        ResponseEntity<Empleado> ree = controllerMock.insertar(e1);

        // THEN:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ree.getStatusCode(), "Salta Exepción");

    }

    @Test
    @Order(3)
    void modificarTest() throws Exception{
        // GIVEN:
        long nuevoSalario = 1500;
        Empleado e1 = new Empleado(username, "nombre", "apel",0);
        Empleado e2 = new Empleado(username, "nombre", "apel",nuevoSalario);

        // WHEN:
        when(serviceMock.modificar(e1)).thenReturn(e2);
        ResponseEntity<Empleado> re = controllerMock.modificar(e1);

        // THEN:
        assertEquals(HttpStatus.NO_CONTENT, re.getStatusCode(), "Comprobación de código de error del servicio");
        assertEquals(e2, re.getBody(), "Mismo Empelado");
        assertEquals(nuevoSalario, re.getBody().getSalario(), "Nuevo Salario");

    }

    @Test
    @Order(4)
    void modificarTestExeption() throws Exception{
        // GIVEN:
        Empleado e = new Empleado(username, "nombre", "ape", 0);

        // WHEN:
        doThrow(new Exception()).when(serviceMock).modificar(e);
        ResponseEntity<Empleado> re = controllerMock.modificar(e);

        // THEN:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode(), "Salta Exepción");

    }

    @Test
    @Order(5)
    void buscarPorUsernameTest() throws Exception{

        // GIVEN:
        Empleado e = new Empleado(username, "nombre", "ape", 0);

        // WHEN:
        when(serviceMock.buscarPorUsername(username)).thenReturn(e);
        ResponseEntity<Empleado> re = controllerMock.buscarPorUsername(username);

        // THEN:
        assertAll(
                () -> assertEquals(HttpStatus.OK, re.getStatusCode(), "Resultado"),
                () -> assertEquals(username, re.getBody().getUsername())

        );

    }

    @Test
    @Order(6)
    void buscarPorUsernameTestExeption() throws Exception{

        // GIVEN:
        Empleado e = new Empleado(username, "nombre", "ape", 0);

        // WHEN:
        doThrow(new Exception()).when(serviceMock).buscarPorUsername(username);
        ResponseEntity<Empleado> re = controllerMock.buscarPorUsername(username);

        // THEN:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode(), "Código de Error");

    }

    @Test
    @Order(7)
    void eliminarTest() throws Exception{
        // GIVEN:
        Empleado e = new Empleado(username, "nombre", "ape", 0);

        // WHEN:
        ResponseEntity<String> re = controllerMock.eliminar(e);

        // THEN:
        assertEquals(HttpStatus.OK, re.getStatusCode(), "Resultado de retorno correcto");
    }

    @Test
    @Order(8)
    void eliminarTestExeption() throws Exception{
        // GIVEN:
        Empleado e = new Empleado(username, "nombre", "ape", 0);

        // WHEN:
        doThrow(new Exception()).when(serviceMock).eliminar(e);
        ResponseEntity<String> re = controllerMock.eliminar(e);

        // THEN:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode(), "Código de Error");
    }

    @Test
    @Order(9)
    void buscarTodos() throws Exception{
        // GIVEN:
        Empleado e1 = new Empleado(username, "nombre", "apel",0);
        Empleado e2 = new Empleado(username, "nombre", "apel",1500);
        List<Empleado> le = Arrays.asList(e1,e2);

        // WHEN:
        when(serviceMock.buscarTodos()).thenReturn(le);
        ResponseEntity<List<Empleado>> re = controllerMock.buscarTodos();

        // THEN:
        assertEquals(HttpStatus.OK, re.getStatusCode(), "Resultado de retorno correcto");
        assertEquals(2, re.getBody().size(), "Devuelve los dos empleados existentes");
    }

    @Test
    @Order(10)
    void buscarTodosTestExeption() throws Exception{
        // GIVEN:

        // WHEN:
        doThrow(new Exception()).when(serviceMock).buscarTodos();
        ResponseEntity<List<Empleado>> re = controllerMock.buscarTodos();

        // THEN:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode(), "Código de Error");
    }

    @Test
    @Order(11)
    void buscarPorNombreTest() throws Exception{

        // GIVEN:
        String nombre = "nombre";
        Empleado e1 = new Empleado(username, "nombre", "apel",0);
        List<Empleado> le = java.util.Arrays.asList(e1);

        // WHEN:
        when(serviceMock.buscarPorNombre(nombre)).thenReturn(le);
        ResponseEntity<List<Empleado>> re = controllerMock.buscarPorNombre(nombre);

        // THEN:
        assertEquals(HttpStatus.OK, re.getStatusCode(), "Resultado de retorno correcto");
        assertEquals(1, re.getBody().size(), "Devuelve el empleado existente");
        assertEquals(nombre, re.getBody().get(0).getNombre());

    }

    @Test
    @Order(12)
    void buscarPorNombreTestExeption() throws Exception{
        // GIVEN:

        // WHEN:
        doThrow(new Exception()).when(serviceMock).buscarPorNombre(ArgumentMatchers.anyString());
        ResponseEntity<List<Empleado>> re = controllerMock.buscarPorNombre(ArgumentMatchers.anyString());

        // THEN:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode(), "Código de Error");
    }

}
