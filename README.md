# Infaut - Colegio Tecnico Nacional

## Sistema de Autenticacion por Huella Digital y Digitalizacion de Hojas Catedra

Este es el repositorio del proyecto Infaut,
un proyecto expuesto en la Expotecnia 2025

> [!NOTE]
> Falta la parte de Arduino. que tengo que ver como organizar esa parte.
> Por ahora quiero hacer en este mismo repositorio

### Funcionalidades

- Autenticacion por huella para la entrada y salida del aula de los alumnos,
  esto se va hacer mediante lectores de huella y modulos Arduino.
- La entrada y salida sera registrada en una base de datos para que el docente tenga
  rastro de quienes estan y quienes no estan en el aula en un cierto momento.
- Se van a digitalizar las hojas catedra con el formato usado en el colegio, permitiendo
  a los docentes imprimir cuando les parezca necesario.

#### Dependencias

- JasperReports para la generacion de reportes, que corresponden a las hojas catedra.
- Java 17 y superior para la ejecucion del proyecto.
- JAvaFX para la interfaz grafica del proyecto.
- La CLI de Arduino para la comunicacion con los modulos Arduino.
