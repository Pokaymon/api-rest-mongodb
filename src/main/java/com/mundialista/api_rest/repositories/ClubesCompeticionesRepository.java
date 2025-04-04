package com.mundialista.api_rest.repositories;

import com.mundialista.api_rest.models.ClubesCompeticiones;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ClubesCompeticionesRepository extends MongoRepository<ClubesCompeticiones, String> {
    List<ClubesCompeticiones> findByClubId(String clubId);
    List<ClubesCompeticiones> findByCompeticionId(String competicionId);
}

