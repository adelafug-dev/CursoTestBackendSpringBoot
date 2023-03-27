package com.everis.everfuture.controller;

import com.everis.everfuture.respository.entity.Empleado;
import com.everis.everfuture.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/empleado")
public class EmpleadoRestController {

    @Autowired
    EmpleadoService empleadoService;

    @PostMapping()
    public ResponseEntity<Empleado> insertar(@RequestBody Empleado nuevo) {
        try {
            Empleado empleadoInsertado = empleadoService.insertar(nuevo);
            return new ResponseEntity<>(empleadoInsertado, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new Empleado(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Empleado> modificar(Empleado nuevo) {
        try {
            Empleado empleadoModificado = empleadoService.modificar(nuevo);
            return new ResponseEntity<>(empleadoModificado, HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(new Empleado(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<Empleado> buscarPorUsername(@PathVariable String username) {
        try {
            return new ResponseEntity<>(empleadoService.buscarPorUsername(username), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new Empleado(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> eliminar(@RequestBody Empleado e) {
        try {
            empleadoService.eliminar(e);
            return new ResponseEntity<>("eliminado correctamente", HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Error desconocido", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> buscarTodos() {
        try {
            return new ResponseEntity<>(empleadoService.buscarTodos(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{nombre}")
    public ResponseEntity<List<Empleado>> buscarPorNombre(@PathVariable String nombre) {
        try {
            return new ResponseEntity<>(empleadoService.buscarPorNombre(nombre), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
