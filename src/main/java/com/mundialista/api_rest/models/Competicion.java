package com.mundialista.api_rest.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "competiciones")
public class Competicion {

    @Id
    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaFin;
    private double montoPremio;
    private String nombre;

    // Constructor vacío
    public Competicion() {}

    // Constructor con parámetros
    public Competicion(String id, Date fechaInicio, Date fechaFin, double montoPremio, String nombre) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.montoPremio = montoPremio;
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getMontoPremio() {
        return montoPremio;
    }

    public void setMontoPremio(double montoPremio) {
        this.montoPremio = montoPremio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

