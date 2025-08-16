package com.Vinoteca.AppVinos.controller;

import com.Vinoteca.AppVinos.models.Oferta;
import com.Vinoteca.AppVinos.repository.OfertaRepository;
import com.Vinoteca.AppVinos.service.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ofertas")
@CrossOrigin(origins = "http://localhost:9002")
public class OfertaController {

    @Autowired //
    private OfertaRepository ofertaRepository;

    @Autowired  // << inyectamos el servicio
    private OfertaService ofertaService;


    // --- MUSTRAS LAS OFERTAS ACTIVAS  ---
    @GetMapping("/ofertas/activas")
    public List<Oferta> getActiveOffers() {
        LocalDate hoy = LocalDate.now();
        return ofertaRepository.findByActivoTrueAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(hoy, hoy);
    }

    // --- AGREGA OFERTAS ---
    @PostMapping("/agregarOferta")
    public ResponseEntity<Oferta> agregarOferta(@RequestBody Oferta oferta) {
        Oferta nuevaOferta = ofertaService.crearOferta(oferta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaOferta);
    }


    // --- EDITAR OFERTA ---
    @PutMapping("/editar/{id}")
    public ResponseEntity<Oferta> editarOferta(@PathVariable Long id, @RequestBody Oferta ofertaActualizada) {
        return ofertaRepository.findById(id)
                .map(oferta -> {
                    oferta.setNombre(ofertaActualizada.getNombre());
                    oferta.setDescripcion(ofertaActualizada.getDescripcion());
                    oferta.setTipoDescuento(ofertaActualizada.getTipoDescuento());
                    oferta.setValorDescuento(ofertaActualizada.getValorDescuento());
                    oferta.setFechaInicio(ofertaActualizada.getFechaInicio());
                    oferta.setFechaFin(ofertaActualizada.getFechaFin());
                    oferta.setActivo(ofertaActualizada.isActivo());
                    oferta.setPrecio(ofertaActualizada.getPrecio());
                    oferta.setVinos(ofertaActualizada.getVinos()); // reasocia los vinos
                    Oferta ofertaGuardada = ofertaRepository.save(oferta);
                    return ResponseEntity.ok(ofertaGuardada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // --- ELIMINAR OFERTA ---
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarOferta(@PathVariable Long id) {
        if (ofertaRepository.existsById(id)) {
            ofertaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

