package com.everis.everfuture.respository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;

@Entity
@Table
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Empleado {

    @Id
    @Column(length = 15)
    private String username;

    @Column
    private String nombre;

    @Column
    private String apellidos;

    @Column
    private long salario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return username.equals(empleado.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

}
