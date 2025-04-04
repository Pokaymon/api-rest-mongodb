package com.mundialista.api_rest.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clubes_jugadores")
public class ClubesJugadores {

    @Id
    private String id;

    @DBRef
    private Club club;

    @DBRef
    private Jugador jugador;

    // Constructor vacío
    public ClubesJugadores() {}

    // Constructor con parámetros
    public ClubesJugadores(Club club, Jugador jugador) {
        this.club = club;
        this.jugador = jugador;
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

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}

