# Infaut - Colegio Tecnico Nacional

## Sistema de Autenticacion por Huella Digital y Digitalizacion de Hojas Catedra

Este es el repositorio del proyecto Infaut,
un proyecto expuesto en la Expotecnia 2025

### Funcionalidades

- Autenticacion por huella para la entrada y salida del aula de los alumnos,
  esto se va hacer mediante lectores de huella y modulos Arduino.
- La entrada y salida sera registrada en una base de datos para que el docente tenga
  rastro de quienes estan y quienes no estan en el aula en un cierto momento.

### Compilar el proyecto

#### Dependencias/Requisitos

##### General

- Java 17 y superior para la ejecucion del proyecto.
- Maven 3.8 o superior
- JAvaFX para la interfaz grafica del programa de administrador.

##### App android (en caso de querer compilar el apk)

- Kotlin, como para el SDK de Android versino 24 o superior
- La APK funciona hasta java 11

##### Arduino (sin terminar)

- La CLI o IDE de Arduino para compilar su respectivo codigo.

#### Como armar el proyecto

##### Parte Java

```sh
mvn clean-package # Compilar el proyecto
```

podes abrir el jar con:

```sh
java - jar ./target/Infaut-1.0-SNAPSHOT.jar
```

o alternativamente podes ejecutar

```sh
mvn exec:java -Dexec.mainClass="ctn.infaut.App" # Ejecutar mediante maven
```

##### Parte Android

La forma más sencilla de instalar es mediante la seccion de
[Releases](https://github.com/Isacc-smth/InfAut/releases)

##### Compilar el apk

En la carpeta root del proyecto (donde esta este archivo), ejecutar:

```sh
# En linux/Mac
./gradlew build
# En Windows
.\gradlew.bat build
```

Una vez finalize
