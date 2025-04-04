package com.mundialista.api_rest.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clubes")
public class Club {

    @Id
    private String id;
    private String nombre;

    @DBRef
    private Entrenador entrenador; // Relación con el entrenador

    @DBRef
    private Asociacion asociacion; // Relación con la Asociacion

    // Constructor vacío
    public Club() {
    }

    // Constructor con parámetros
    public Club(String nombre, Entrenador entrenador, Asociacion asociacion) {
        this.nombre = nombre;
        this.entrenador = entrenador;
	this.asociacion = asociacion;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Asociacion getAsociacion() {
	return asociacion;
    }

    public void setAsociacion(Asociacion asociacion) {
	this.asociacion = asociacion;
    }
}

