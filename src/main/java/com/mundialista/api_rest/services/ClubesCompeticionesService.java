package com.mundialista.api_rest.services;

import com.mundialista.api_rest.models.Club;
import com.mundialista.api_rest.models.ClubesCompeticiones;
import com.mundialista.api_rest.models.Competicion;
import com.mundialista.api_rest.repositories.ClubRepository;
import com.mundialista.api_rest.repositories.ClubesCompeticionesRepository;
import com.mundialista.api_rest.repositories.CompeticionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClubesCompeticionesService {

    @Autowired
    private ClubesCompeticionesRepository clubesCompeticionesRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private CompeticionRepository competicionRepository;

    // Crear una nueva relación entre un club y una competición
    public ClubesCompeticiones crearRelacion(String clubId, String competicionId) {
        Optional<Club> clubOpt = clubRepository.findById(clubId);
        Optional<Competicion> competicionOpt = competicionRepository.findById(competicionId);

        if (clubOpt.isEmpty() || competicionOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Club o Competición no encontrados");
        }

	// Verificar si ya existe esa relación
        Optional<ClubesCompeticiones> existente = clubesCompeticionesRepository.findByClubIdAndCompeticionId(clubId, competicionId);
        if (existente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El club ya está asociado a esta competición");
        }

        ClubesCompeticiones nuevaRelacion = new ClubesCompeticiones(clubOpt.get(), competicionOpt.get());
        return clubesCompeticionesRepository.save(nuevaRelacion);
    }


    public ClubesCompeticiones obtenerPorId(String id) {
        return clubesCompeticionesRepository.findById(id).orElse(null);
    }

    // Obtener todas las relaciones
    public List<ClubesCompeticiones> obtenerTodas() {
        return clubesCompeticionesRepository.findAll();
    }

    // Obtener relaciones por club
    public List<ClubesCompeticiones> obtenerPorClub(String clubId) {
        return clubesCompeticionesRepository.findByClubId(clubId);
    }

    // Obtener relaciones por competición
    public List<ClubesCompeticiones> obtenerPorCompeticion(String competicionId) {
        return clubesCompeticionesRepository.findByCompeticionId(competicionId);
    }

    // Eliminar una relación
    public void eliminarRelacion(String id) {
        if (!clubesCompeticionesRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relación no encontrada");
        }
        clubesCompeticionesRepository.deleteById(id);
    }
}

