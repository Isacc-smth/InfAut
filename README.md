# Infaut - Colegio Tecnico Nacional

## Sistema de Autenticacion por Huella Digital y Digitalizacion de Hojas Catedra

Este es el repositorio del proyecto Infaut, un proyecto originalmente 
expuesto en la Expotecnia 2025, organizada anualmente por el colegio.
El sistema esta estructurado para facilitar la distribucion e implementacion
en otras instituciones del pais.

## POR HACER:
- Finalizar el vinculo entre entidades 'varios a varios'. 
- Implementar la autenticacion por huella.
- Dar instrucciones y sugerencias de como hacerlo. Separar las instrucciones en [SETUP](setup.md)
- Limpiar el codigo y documentar via JavaDoc. Notará que gradualmente lo estoy haciendo

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
- PostgreSQL. Si desea usar otro motor SQL como MySQL, debera adaptar el script.

#### Armar el proyecto

> [!NOTE]
> NO es necesario seguir estos pasos si esta trabajando desde un IDE, vscode
> u otro editor que automatice el compilado y ejecucion.
> Solo siga estos pasos si realmente necesita ejecutar desde una terminal/cmd

```sh
mvn clean-package # Compilar el proyecto en un jar
```

puede abrir el jar con:

```sh
java - jar ./target/Infaut-1.0-SNAPSHOT.jar
```

o alternativamente puede ejecutar

```sh
mvn exec:java -Dexec.mainClass="ctn.infaut.App" # Ejecutar mediante maven
```

#### Replicar la base de datos y proporcionarla al proyecto

1. Utlice el [script SQL](crear_DB.sql)
2. Cree un archivo `db.properties`
3. Especifique las credenciales con el siguiente formato.
```sh
   url=jdbc:postgresql://[host]:[puerto, por defecto es 5432]/[nombre de la DB] 
```
4. Quizá tenga que agregar:
```sh
    db.user={usuario}
    db.password={contraseña}
```
