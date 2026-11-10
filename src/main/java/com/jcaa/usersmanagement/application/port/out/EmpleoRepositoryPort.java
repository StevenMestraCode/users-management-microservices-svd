package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Empleo;
import java.util.List;
import java.util.Optional;

public interface EmpleoRepositoryPort {
    Empleo save(Empleo empleo);

    Empleo update(Empleo empleo); // <-- ESTA ES LA LÍNEA QUE FALTA

    Optional<Empleo> findById(Long id);
    List<Empleo> findAll();
    void deleteById(Long id);
}