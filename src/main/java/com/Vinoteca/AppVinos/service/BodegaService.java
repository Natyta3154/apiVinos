package com.Vinoteca.AppVinos.service;

import com.Vinoteca.AppVinos.models.Bodegas;
import com.Vinoteca.AppVinos.repository.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BodegaService {

    private final BodegaRepository bodegaRepository;

    // La inyección de dependencias se realiza a través del constructor
    @Autowired
    public BodegaService(BodegaRepository bodegaRepository) {
        this.bodegaRepository = bodegaRepository;
    }

    public List<Bodegas> listarTodas() {
        // Llama a la consulta personalizada del repositorio
        return bodegaRepository.findAllWithVinos();
    }

    public Optional<Bodegas> buscarPorId(long id) { // Corregido a Long
        return bodegaRepository.findById(id);
    }

    public Optional<Bodegas> buscarPorNombre(String nombre) {
        return bodegaRepository.findByNombre(nombre);
    }

    public Bodegas guardar(Bodegas bodega) {
        // Se elimina la validación de stock y precio porque son de la clase Vinos
        return bodegaRepository.save(bodega);
    }

    public Optional<Bodegas> actualizar(long id, Bodegas bodegaActualizada) { // Corregido a Long y Optional
        return bodegaRepository.findById(id).map(b -> {
            // Se actualizan solo los campos de la bodega
            b.setNombre(bodegaActualizada.getNombre());
            b.setPais(bodegaActualizada.getPais()); // Corregido el typo
            b.setRegion(bodegaActualizada.getRegion());
            b.setDireccion(bodegaActualizada.getDireccion());
            b.setDescripcion(bodegaActualizada.getDescripcion());
            b.setUrlWeb(bodegaActualizada.getUrlWeb());
            b.setImagenUrl(bodegaActualizada.getImagenUrl());
            return bodegaRepository.save(b);
        });
    }

    public void eliminar(long id) { // Corregido a Long y tipo de retorno void
        bodegaRepository.deleteById(id);
    }
}
