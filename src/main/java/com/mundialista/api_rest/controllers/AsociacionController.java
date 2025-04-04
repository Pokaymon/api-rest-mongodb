package com.mundialista.api_rest.controllers;

import com.mundialista.api_rest.models.Asociacion;
import com.mundialista.api_rest.services.AsociacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asociaciones")
public class AsociacionController {

    @Autowired
    private AsociacionService asociacionService;

    // Obtener todas las asociaciones
    @GetMapping
    public List<Asociacion> obtenerTodas() {
        return asociacionService.obtenerTodas();
    }

    // Obtener una asociación por ID
    @GetMapping("/{id}")
    public Asociacion obtenerPorId(@PathVariable String id) {
        return asociacionService.obtenerPorId(id);
    }

    // Crear una nueva asociación
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Asociacion crearAsociacion(@RequestBody Asociacion asociacion) {
        return asociacionService.crearAsociacion(asociacion);
    }

    // Actualizar una asociación existente
    @PutMapping("/{id}")
    public Asociacion actualizarAsociacion(@PathVariable String id, @RequestBody Asociacion asociacion) {
        return asociacionService.actualizarAsociacion(id, asociacion);
    }

    // Eliminar una asociación
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarAsociacion(@PathVariable String id) {
        asociacionService.eliminarAsociacion(id);
    }
}

