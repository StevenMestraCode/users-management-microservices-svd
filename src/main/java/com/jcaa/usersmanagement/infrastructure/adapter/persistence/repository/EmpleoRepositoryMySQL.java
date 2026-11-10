package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.EmpleoRepositoryPort;
import com.jcaa.usersmanagement.domain.model.Empleo;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.EmpleoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.EmpleoPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmpleoRepositoryMySQL implements EmpleoRepositoryPort {

    private static final String SQL_INSERT =
            "INSERT INTO empleos (nombre, categoria, area_trabajo, empresa, nivel, sueldo, funciones, cargo_jefe) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE empleos SET nombre = ?, categoria = ?, area_trabajo = ?, empresa = ?, nivel = ?, sueldo = ?, funciones = ?, cargo_jefe = ? " +
                    "WHERE id = ?";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, nombre, categoria, area_trabajo, empresa, nivel, sueldo, funciones, cargo_jefe " +
                    "FROM empleos WHERE id = ? LIMIT 1";

    private static final String SQL_SELECT_ALL =
            "SELECT id, nombre, categoria, area_trabajo, empresa, nivel, sueldo, funciones, cargo_jefe " +
                    "FROM empleos ORDER BY nombre ASC";

    private static final String SQL_DELETE =
            "DELETE FROM empleos WHERE id = ?";

    private final DataSource dataSource;

    @Override
    public Empleo save(final Empleo empleo) {
        final EmpleoPersistenceDto dto = EmpleoPersistenceMapper.fromModelToDto(empleo);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, dto.nombre());
            statement.setString(2, dto.categoria());
            statement.setString(3, dto.areaTrabajo());
            statement.setString(4, dto.empresa());
            statement.setString(5, dto.nivel());
            statement.setDouble(6, dto.sueldo());
            statement.setString(7, dto.funciones());
            statement.setString(8, dto.cargoJefe());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    empleo.setId(generatedKeys.getLong(1));
                }
            }
            return empleo;
        } catch (SQLException exception) {
            throw PersistenceException.becauseSaveFailed(String.valueOf(dto.nombre()), exception);
        }
    }

    @Override
    public Empleo update(final Empleo empleo) {
        final EmpleoPersistenceDto dto = EmpleoPersistenceMapper.fromModelToDto(empleo);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {

            statement.setString(1, dto.nombre());
            statement.setString(2, dto.categoria());
            statement.setString(3, dto.areaTrabajo());
            statement.setString(4, dto.empresa());
            statement.setString(5, dto.nivel());
            statement.setDouble(6, dto.sueldo());
            statement.setString(7, dto.funciones());
            statement.setString(8, dto.cargoJefe());
            statement.setLong(9, dto.id());
            statement.executeUpdate();

            return empleo;
        } catch (SQLException exception) {
            throw PersistenceException.becauseUpdateFailed(String.valueOf(dto.id()), exception);
        }
    }

    @Override
    public Optional<Empleo> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }
                return Optional.of(EmpleoPersistenceMapper.fromResultSetToModel(resultSet));
            }
        } catch (SQLException exception) {
            throw PersistenceException.becauseFindByIdFailed(String.valueOf(id), exception);
        }
    }

    @Override
    public List<Empleo> findAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            return EmpleoPersistenceMapper.fromResultSetToModelList(resultSet);
        } catch (SQLException exception) {
            throw PersistenceException.becauseFindAllFailed(exception);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw PersistenceException.becauseDeleteFailed(String.valueOf(id), exception);
        }
    }
}