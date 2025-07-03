# Infaut - Colegio Tecnico Nacional

## Sistema de Autenticacion por Huella Digital y Digitalizacion de Hojas Catedra

Este es el repositorio del proyecto Infaut,
un proyecto expuesto en la Expotecnia 2025

> [!CAUTION]
> Falta la parte de Arduino. que tengo que ver como organizar esa parte.
> Por ahora quiero hacer en este mismo repositorio

### Funcionalidades

- Autenticacion por huella para la entrada y salida del aula de los alumnos,
  esto se va hacer mediante lectores de huella y modulos Arduino.
- La entrada y salida sera registrada en una base de datos para que el docente tenga
  rastro de quienes estan y quienes no estan en el aula en un cierto momento.

### Compilar el proyecto

#### Dependencias/Requisitos

- Java 17 y superior para la ejecucion del proyecto.
- Maven 3.8 o superior
- JAvaFX para la interfaz grafica del proyecto.
- La CLI o IDE de Arduino para compilar su respectivo codigo.

#### Como armar el proyecto

##### Parte Java

```sh
mvn clean-package # Compilar el proyecto
```

puedes abrir el jar en:

```sh
./target/Infaut-1.0-SNAPSHOT.jar # No creo que cambie la version porque realmente no importa
```

o alternativamente puedes ejecutar

```sh
mvn exec:java -Dexec.mainClass="ctn.infaut.App" # Ejecutar mediante maven
```

> [!CAUTION]
> Faltan tambien instrucciones de como compilar la apk, con gradle
