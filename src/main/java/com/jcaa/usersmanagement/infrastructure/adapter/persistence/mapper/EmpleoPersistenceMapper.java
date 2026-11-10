package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.Empleo;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.EmpleoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.EmpleoEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class EmpleoPersistenceMapper {

    public EmpleoPersistenceDto fromModelToDto(final Empleo empleo) {
        return new EmpleoPersistenceDto(
                empleo.getId(),
                empleo.getNombre(),
                empleo.getCategoria(),
                empleo.getAreaTrabajo(),
                empleo.getEmpresa(),
                empleo.getNivel(),
                empleo.getSueldo(),
                empleo.getFunciones(),
                empleo.getCargoJefe()
        );
    }

    public EmpleoEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
        return new EmpleoEntity(
                resultSet.getLong("id"),
                resultSet.getString("nombre"),
                resultSet.getString("categoria"),
                resultSet.getString("area_trabajo"),
                resultSet.getString("empresa"),
                resultSet.getString("nivel"),
                resultSet.getDouble("sueldo"),
                resultSet.getString("funciones"),
                resultSet.getString("cargo_jefe")
        );
    }

    public Empleo fromEntityToModel(final EmpleoEntity entity) {
        return new Empleo(
                entity.id(),
                entity.nombre(),
                entity.categoria(),
                entity.areaTrabajo(),
                entity.empresa(),
                entity.nivel(),
                entity.sueldo(),
                entity.funciones(),
                entity.cargoJefe()
        );
    }

    public Empleo fromResultSetToModel(final ResultSet resultSet) throws SQLException {
        return fromEntityToModel(fromResultSetToEntity(resultSet));
    }

    public List<Empleo> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
        final List<Empleo> empleos = new ArrayList<>();
        while (resultSet.next()) {
            empleos.add(fromResultSetToModel(resultSet));
        }
        return empleos;
    }
}