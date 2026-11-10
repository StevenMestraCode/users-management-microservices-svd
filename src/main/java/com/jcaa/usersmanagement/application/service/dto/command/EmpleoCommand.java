package com.jcaa.usersmanagement.application.service.dto.command;

public record EmpleoCommand(
        Long id,
        String nombre,
        String categoria,
        String areaTrabajo,
        String empresa,
        String nivel,
        Double sueldo,
        String funciones,
        String cargoJefe
) {}
