-- =============================================
-- Script de creación de la base de datos
-- Gestión de Usuarios - Arquitectura Hexagonal
-- =============================================

CREATE DATABASE IF NOT EXISTS usuarios_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE usuarios_db;

CREATE TABLE IF NOT EXISTS users (
    id          VARCHAR(36)  NOT NULL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        ENUM('ADMIN', 'MEMBER', 'REVIEWER') NOT NULL,
    status      ENUM('ACTIVE', 'INACTIVE', 'PENDING', 'BLOCKED') NOT NULL DEFAULT 'PENDING',
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Usuario administrador inicial (password: Admin1234!)
INSERT INTO users (id, name, email, password, role, status)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    'Administrador',
    'admin@example.com',
    '$2a$12$sfwkajsls.fktQb0Ngw5cOiSiV3z4LIwbucSAy4sw2wyNlxbUBt4q',
    'ADMIN',
    'ACTIVE'
);
CREATE TABLE IF NOT EXISTS empleos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(255),
    area_trabajo VARCHAR(255),
    empresa VARCHAR(255),
    nivel VARCHAR(50),
    sueldo DOUBLE,
    funciones TEXT,
    cargo_jefe VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
