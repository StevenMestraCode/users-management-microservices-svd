package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record EmpleoEntity(
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
