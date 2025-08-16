package com.Vinoteca.AppVinos.service;


import com.Vinoteca.AppVinos.models.Vinos;
import com.Vinoteca.AppVinos.repository.VinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class VinoService {
    @Autowired
    private VinoRepository vinoRepository;

    public List<Vinos> listarTodos(){return vinoRepository.findAll();}

    public Optional<Vinos> buscarPorId(Long id){ return vinoRepository.findById(id);}

    public Optional<Vinos> buscarPorNombre(String nombre){return vinoRepository.findByNombre(nombre); }

    /* logica guardar Productos*/
    public Vinos guardar(Vinos vino){
        BigDecimal zero = BigDecimal.ZERO;
        if (vino.getStock() < 0 || vino.getPrecio().compareTo(zero) < 0){
            throw new IllegalArgumentException("El stock y el precio no puede ser negativo");
        }
        return vinoRepository.save(vino);
    }

    public Vinos actualizar(Long id, Vinos vinoActualizado){
        return vinoRepository.findById(id).map(v ->{
            BigDecimal zero = BigDecimal.ZERO;

            if (vinoActualizado.getPrecio() != null && vinoActualizado.getPrecio().compareTo(zero) >= 0) {
                v.setPrecio(vinoActualizado.getPrecio());
            }

            if (vinoActualizado.getStock() >= 0) {
                v.setStock(vinoActualizado.getStock());
            }

            v.setNombre(vinoActualizado.getNombre());
            v.setAnejo(vinoActualizado.getAnejo());
            v.setTipoDeVino(vinoActualizado.getTipoDeVino());
            v.setTipoUva(vinoActualizado.getTipoUva());
            v.setDescripcion(vinoActualizado.getDescripcion());
            v.setLitro(vinoActualizado.getLitro());
            v.setAlcohol(vinoActualizado.getAlcohol());
            v.setImagenUrl(vinoActualizado.getImagenUrl());

            return vinoRepository.save(v);
        }).orElseThrow(() -> new RuntimeException("Vino no encontrado con ID: " + id));
    }

    public void eliminar(Long id){vinoRepository.deleteById(id);}

}
