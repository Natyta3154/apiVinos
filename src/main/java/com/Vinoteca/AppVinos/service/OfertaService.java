package com.Vinoteca.AppVinos.service;

import com.Vinoteca.AppVinos.models.Oferta;
import com.Vinoteca.AppVinos.models.Vinos;
import com.Vinoteca.AppVinos.repository.OfertaRepository;
import com.Vinoteca.AppVinos.repository.VinoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class OfertaService {

    private final OfertaRepository ofertaRepository;
    private final VinoRepository vinoRepository;

    public OfertaService(OfertaRepository ofertaRepository, VinoRepository vinoRepository) {
        this.ofertaRepository = ofertaRepository;
        this.vinoRepository = vinoRepository;
    }



    @Transactional
    public Oferta crearOferta(Oferta oferta) {
        // Primero guardamos la oferta sin los vinos
        Set<Vinos> vinos = oferta.getVinos();
        oferta.setVinos(new HashSet<>()); // evita problemas con insert de la tabla intermedia
        Oferta ofertaGuardada = ofertaRepository.save(oferta);

        // Si hay vinos asociados, los cargamos y los seteamos
        if (vinos != null && !vinos.isEmpty()) {
            Set<Vinos> vinosAsociados = new HashSet<>();
            for (Vinos vino : vinos) {
                Vinos vinoExistente = vinoRepository.findById(vino.getId())
                        .orElseThrow(() -> new RuntimeException("Vino no encontrado: " + vino.getId()));
                vinosAsociados.add(vinoExistente);
            }
            ofertaGuardada.setVinos(vinosAsociados);
            // Guardamos de nuevo para persistir la relación
            ofertaGuardada = ofertaRepository.save(ofertaGuardada);
        }

        return ofertaGuardada;
    }

    // ===================== NUEVOS MÉTODOS =====================
    @Transactional
    public Oferta actualizarOferta(Long id, Oferta ofertaDetalles) {
        Oferta ofertaExistente = ofertaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada con id: " + id));

        ofertaExistente.setNombre(ofertaDetalles.getNombre());
        ofertaExistente.setDescripcion(ofertaDetalles.getDescripcion());
        ofertaExistente.setTipoDescuento(ofertaDetalles.getTipoDescuento());
        ofertaExistente.setValorDescuento(ofertaDetalles.getValorDescuento());
        ofertaExistente.setFechaInicio(ofertaDetalles.getFechaInicio());
        ofertaExistente.setFechaFin(ofertaDetalles.getFechaFin());
        ofertaExistente.setActivo(ofertaDetalles.isActivo());
        ofertaExistente.setPrecio(ofertaDetalles.getPrecio());

        // Actualizamos la relación con vinos
        Set<Vinos> vinosActualizados = new HashSet<>();
        if (ofertaDetalles.getVinos() != null) {
            for (Vinos vino : ofertaDetalles.getVinos()) {
                Vinos vinoExistente = vinoRepository.findById(vino.getId())
                        .orElseThrow(() -> new RuntimeException("Vino no encontrado: " + vino.getId()));
                vinosActualizados.add(vinoExistente);
            }
        }
        ofertaExistente.setVinos(vinosActualizados);

        return ofertaRepository.save(ofertaExistente);
    }

    @Transactional
    public void eliminarOferta(Long id) {
        if (!ofertaRepository.existsById(id)) {
            throw new RuntimeException("Oferta no encontrada con id: " + id);
        }
        ofertaRepository.deleteById(id);
    }
}

