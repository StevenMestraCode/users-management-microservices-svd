package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.application.service.EmpleoService;
import com.jcaa.usersmanagement.application.service.dto.command.EmpleoCommand;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.EmpleoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.EmpleoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/empleos")
@RequiredArgsConstructor
public class EmpleoController {

    private final EmpleoService empleoService;

    @PostMapping
    public ResponseEntity<EmpleoResponse> crear(@RequestBody EmpleoRequest request) {
        EmpleoCommand command = new EmpleoCommand(
                null, request.nombre(), request.categoria(), request.areaTrabajo(),
                request.empresa(), request.nivel(), request.sueldo(), request.funciones(), request.cargoJefe()
        );
        return new ResponseEntity<>(EmpleoResponse.fromDomain(empleoService.crear(command)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmpleoResponse>> listar() {
        List<EmpleoResponse> lista = empleoService.listarTodos().stream()
                .map(EmpleoResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleoResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(EmpleoResponse.fromDomain(empleoService.obtenerPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleoResponse> actualizar(@PathVariable Long id, @RequestBody EmpleoRequest request) {
        EmpleoCommand command = new EmpleoCommand(
                id, request.nombre(), request.categoria(), request.areaTrabajo(),
                request.empresa(), request.nivel(), request.sueldo(), request.funciones(), request.cargoJefe()
        );
        return ResponseEntity.ok(EmpleoResponse.fromDomain(empleoService.actualizar(id, command)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empleoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
