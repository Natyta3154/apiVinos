package com.Vinoteca.AppVinos.repository;

import com.Vinoteca.AppVinos.models.Bodegas;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BodegaRepository extends JpaRepository<Bodegas, Long> {
    //@Override
    Optional<Bodegas> findByNombre(String nombre);



    // Método para obtener todas las bodegas junto con sus vinos,
    // utilizando una consulta personalizada con LEFT JOIN FETCH para evitar el problema del N+1
    // y asegurando que se devuelvan todas las bodegas, incluso las que no tienen vinos.
    @Query("SELECT b FROM Bodegas b LEFT JOIN FETCH b.vinos")
    List<Bodegas> findAllWithVinos();
}
