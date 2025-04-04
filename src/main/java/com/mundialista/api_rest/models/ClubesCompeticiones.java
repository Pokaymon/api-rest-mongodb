package com.mundialista.api_rest.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clubes_competiciones")
public class ClubesCompeticiones {

    @Id
    private String id;

    @DBRef
    private Club club;

    @DBRef
    private Competicion competicion;

    // Constructor vacío
    public ClubesCompeticiones() {}

    // Constructor con parámetros
    public ClubesCompeticiones(Club club, Competicion competicion) {
        this.club = club;
        this.competicion = competicion;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Competicion getCompeticion() {
        return competicion;
    }

    public void setCompeticion(Competicion competicion) {
        this.competicion = competicion;
    }
}

