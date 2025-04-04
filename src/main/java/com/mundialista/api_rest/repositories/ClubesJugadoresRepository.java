package com.mundialista.api_rest.repositories;

import com.mundialista.api_rest.models.ClubesJugadores;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ClubesJugadoresRepository extends MongoRepository<ClubesJugadores, String> {
    List<ClubesJugadores> findByClubId(String clubId);
    List<ClubesJugadores> findByJugadorId(String jugadorId);
}
