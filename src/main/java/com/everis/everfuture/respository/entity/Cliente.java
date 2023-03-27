package com.everis.everfuture.respository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cliente {

    @Id
    @Column(length = 10)
    private String dni;

    @Column
    private String name;

    @Column
    private String appellidos;

    @Column
    private String direccion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return dni.equals(cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

}
