-- Version para MySQL

CREATE DATABASE IF NOT EXISTS infaut;
USE infaut;

-- Tabla de cursos
CREATE TABLE curso (
    id_curso INT AUTO_INCREMENT NOT NULL,
    descripcion TEXT NOT NULL, PRIMARY KEY (id_curso)
);

-- Tabla de aulas
CREATE TABLE aula (
    id_aula INT AUTO_INCREMENT NOT NULL,
    descripcion TEXT NOT NULL,
    PRIMARY KEY (id_aula)
);

-- Tabla de docentes
CREATE TABLE docente (
    id_docente INT AUTO_INCREMENT NOT NULL,
    nombre TEXT NOT NULL,
    apellido TEXT NOT NULL,
    ci INT NOT NULL,
    PRIMARY KEY (id_docente)
);

-- Tabla de alumnos
CREATE TABLE alumno (
    id_alumno INT AUTO_INCREMENT NOT NULL,
    nombre TEXT NOT NULL,
    apellido TEXT NOT NULL,
    ci INT NOT NULL,
    id_curso INT NOT NULL,
    PRIMARY KEY (id_alumno),
    FOREIGN KEY (id_curso) REFERENCES curso (id_curso)
	ON DELETE NO ACTION
	ON UPDATE CASCADE
);

-- Tabla de huellas
CREATE TABLE huella (
    id_huella INT AUTO_INCREMENT NOT NULL,
    imagen_huella LONGBLOB NOT NULL,
    id_alumno INT NOT NULL,
	hora_entrada TIME NOT NULL,
    PRIMARY KEY (id_huella),
    FOREIGN KEY (id_alumno) REFERENCES alumno (id_alumno)
	ON DELETE NO ACTION
	ON UPDATE CASCADE
);

-- Tabla de materias
CREATE TABLE materia (
    id_materia INT AUTO_INCREMENT NOT NULL,
    nombre TEXT NOT NULL,
	-- NOTE: El formato de la hora se valida antes de insertar
    hora_inicio TEXT NOT NULL,  
    hora_fin TEXT NOT NULL,
    aula_id_aula INT NOT NULL,
    PRIMARY KEY (id_materia),
    FOREIGN KEY (aula_id_aula) REFERENCES aula (id_aula)
	ON DELETE NO ACTION
	ON UPDATE CASCADE
);

-- Relacion muchos a muchos: curso <-> materia
CREATE TABLE curso_materia (
    id_materia INT NOT NULL,
    id_curso INT NOT NULL,
    FOREIGN KEY (id_curso) REFERENCES curso (id_curso) ON UPDATE CASCADE ON DELETE NO ACTION,
    FOREIGN KEY (id_materia) REFERENCES materia (id_materia) ON UPDATE CASCADE ON DELETE NO ACTION
);

-- Relacion muchos a muchos: docente <-> materia
CREATE TABLE docente_materia (
    id_docente INT NOT NULL,
    id_materia INT NOT NULL,
    FOREIGN KEY (id_docente) REFERENCES docente (id_docente)
	ON DELETE NO ACTION
	ON UPDATE CASCADE,
    FOREIGN KEY (id_materia) REFERENCES materia (id_materia)
	ON DELETE NO ACTION
	ON UPDATE CASCADE
);
