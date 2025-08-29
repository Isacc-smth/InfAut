# Infaut - Colegio Tecnico Nacional

## Sistema de Autenticacion por Huella Digital y Digitalizacion de Hojas Catedra

Este es el repositorio del proyecto Infaut, un proyecto originalmente 
expuesto en la Expotecnia 2025, organizada anualmente por el colegio.
El sistema esta estructurado para facilitar la distribucion e implementacion
en otras instituciones del pais.

## POR HACER:
- Finalizar el vinculo entre entidades 'vairos a varios'. 
- Implementar la autenticacion por huella.
- Dar instrucciones y sugerencias de como hacerlo.
- Limpiar el codigo y documentar via JavaDoc

### Funcionalidades

- Autenticacion por huella para la entrada y salida del aula de los alumnos,
  esto se va hacer mediante lectores de huella y modulos Arduino.
- La entrada y salida sera registrada en una base de datos para que el docente tenga
  rastro de quienes estan y quienes no estan en el aula en un cierto momento.
- El proyecto originalmente usaba un DBaaS (SupaBase) pero tiene a su disposicion un 
  script SQL (solo Postgres) para replicar la base de datos. 

### Compilar el proyecto

#### Requisitos

> [!IMPORTANT]
> Los requisitos pueden cambiar sin aviso previo. Es aconsejable consultar este
> repositorio periodicamente para ahorrarte errores (y dolores de cabeza)

##### General

- Java 21 y superior para la ejecucion del proyecto.
- Maven 4.0 o superior
- PostgreSQL. Si desea usar otro motor SQL, debera hacer cambios en el script.

#### Como armar el proyecto

> [!CAUTION]
> NO es necesario seguir estos pasos si estas trabajando desde un IDE, vscode.
> Solo siga estos pasos si realmente necesita ejecutar desde una terminal/cmd

##### Parte Java

```sh
mvn clean-package # Compilar el proyecto en un jar
```

podes abrir el jar con:

```sh
java - jar ./target/Infaut-1.0-SNAPSHOT.jar
```

o alternativamente podes ejecutar

```sh
mvn exec:java -Dexec.mainClass="ctn.infaut.App" # Ejecutar mediante maven
```
