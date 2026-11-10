package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto;

import com.jcaa.usersmanagement.domain.model.Empleo;

public record EmpleoResponse(
        Long id,
        String nombre,
        String categoria,
        String areaTrabajo,
        String empresa,
        String nivel,
        Double sueldo,
        String funciones,
        String cargoJefe
) {
    public static EmpleoResponse fromDomain(Empleo empleo) {
        return new EmpleoResponse(
                empleo.getId(), empleo.getNombre(), empleo.getCategoria(), empleo.getAreaTrabajo(),
                empleo.getEmpresa(), empleo.getNivel(), empleo.getSueldo(), empleo.getFunciones(), empleo.getCargoJefe()
        );
    }
}
