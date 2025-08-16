package com.Vinoteca.AppVinos.repository;

import com.Vinoteca.AppVinos.models.Vinos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VinoRepository  extends JpaRepository<Vinos,Long> {
    //@Override
    Optional<Vinos> findByNombre(String nombre);
}
