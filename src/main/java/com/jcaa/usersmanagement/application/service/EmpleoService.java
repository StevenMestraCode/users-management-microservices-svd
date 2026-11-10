package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.out.EmpleoRepositoryPort;
import com.jcaa.usersmanagement.application.service.dto.command.EmpleoCommand;
import com.jcaa.usersmanagement.domain.model.Empleo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleoService {

    private final EmpleoRepositoryPort repositoryPort;

    public Empleo crear(EmpleoCommand command) {
        Empleo empleo = new Empleo(
                null, command.nombre(), command.categoria(), command.areaTrabajo(),
                command.empresa(), command.nivel(), command.sueldo(), command.funciones(), command.cargoJefe()
        );
        return repositoryPort.save(empleo);
    }

    public Empleo actualizar(Long id, EmpleoCommand command) {
        Empleo empleo = new Empleo(
                id, command.nombre(), command.categoria(), command.areaTrabajo(),
                command.empresa(), command.nivel(), command.sueldo(), command.funciones(), command.cargoJefe()
        );
        return repositoryPort.update(empleo);
    }

    public Empleo obtenerPorId(Long id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleo no encontrado con ID: " + id));
    }

    public List<Empleo> listarTodos() {
        return repositoryPort.findAll();
    }

    public void eliminar(Long id) {
        repositoryPort.deleteById(id);
    }
}
