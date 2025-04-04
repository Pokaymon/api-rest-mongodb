package com.mundialista.api_rest.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "asociaciones")
public class Asociacion {
    @Id
    private String id;
    private String nombre;
    private String pais;
    private String presidente;
    private String siglas;

    // Constructores
    public Asociacion() {}

    public Asociacion(String nombre, String pais, String presidente, String siglas) {
        this.nombre = nombre;
        this.pais = pais;
        this.presidente = presidente;
        this.siglas = siglas;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getPresidente() { return presidente; }
    public void setPresidente(String presidente) { this.presidente = presidente; }

    public String getSiglas() { return siglas; }
    public void setSiglas(String siglas) { this.siglas = siglas; }
}

