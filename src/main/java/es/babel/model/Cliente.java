package es.babel.model;

import lombok.Data;

@Data
public class Cliente {
    private String nombre;

    public Cliente() {}

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

}
