package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto;

public record EmpleoRequest(
        String nombre,
        String categoria,
        String areaTrabajo,
        String empresa,
        String nivel,
        Double sueldo,
        String funciones,
        String cargoJefe
) {}