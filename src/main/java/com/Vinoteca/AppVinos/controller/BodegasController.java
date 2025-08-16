package com.Vinoteca.AppVinos.controller;

import com.Vinoteca.AppVinos.dto.BodegaDTO;
import com.Vinoteca.AppVinos.models.Bodegas;
import com.Vinoteca.AppVinos.service.BodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:9002")
@RestController
@RequestMapping("/api/bodegas")

public class BodegasController {

    private final BodegaService bodegaService;

    @Autowired // La inyección de dependencias es en el constructor
    public BodegasController(BodegaService bodegaService) {
        this.bodegaService = bodegaService;
    }



    // Método que devuelve todas las bodegas como DTOs
    @GetMapping("/listaBodegas")
    public List<BodegaDTO> listarBodegas() {
        // La lógica del stock total se maneja aquí al convertir a DTO
        return bodegaService.listarTodas().stream()
                .map(BodegaDTO::new)
                .collect(Collectors.toList());
    }

    // Endpoint para buscar una bodega por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Bodegas> buscarBodegaPorId(@PathVariable long id) {
        return bodegaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para buscar una bodega por su nombre
    @GetMapping("/buscar")
    public ResponseEntity<Bodegas> buscarPorNombre(@RequestParam String nombre) {
        return bodegaService.buscarPorNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para agregar una nueva bodega
    @PostMapping("agregarbodega")
    public ResponseEntity<?> agregarBodega(@RequestBody Bodegas bodega) {
        try {
            Bodegas nuevaBodega = bodegaService.guardar(bodega);
            return ResponseEntity.ok(nuevaBodega);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para actualizar una bodega existente
    @PutMapping("/{id}")
    public ResponseEntity<Bodegas> actualizarBodega(@PathVariable long id, @RequestBody Bodegas bodegaActualizada) {
        return bodegaService.actualizar(id, bodegaActualizada)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para eliminar una bodega
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBodega(@PathVariable long id) {
        bodegaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
