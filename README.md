# RestPruebaEmpleados

API RESTful para la gestión de empleados desarrollada con Java 17, Spring Boot 3, JPA y PostgreSQL. Incluye validaciones, manejo de errores, documentación Swagger, pruebas unitarias y manejo de logs.

---

## Tecnologías

- Java 17
- Spring Boot 3.5.0
- Spring Data JPA
- PostgreSQL
- Liquibase
- Jakarta Validation
- Lombok
- Swagger (springdoc-openapi)
- JUnit 5 + Mockito

---

## Características

- Operaciones CRUD para empleados
- Validaciones automáticas con Jakarta Validation
- Documentación interactiva con Swagger UI (FIX)
- Pruebas unitarias con JUnit y Mockito
- Arquitectura basada en capas (controller, service, repository, mapper)

---

## Endpoints principales
```

Crear un empleado

POST /employees/create

```

```

Crear múltiples empleados

POST /employees/create/many

```

```

Obtener todos los empleados

GET /employees

```

```

Obtener un empleado por ID

GET /employees/{id}

```

```

Actualizar un empleado

PUT /employees/update/{id}

```

```

Eliminar un empleado

DELETE /employees/delete/{id}

```
---
## Documentación Swagger

```

Después de iniciar la aplicación, accede a: 

http://localhost:8080/swagger-ui.html

```
---
## Pruebas
```

Para ejecutar las pruebas unitarias:

./mvnw test
```

Incluye pruebas de capa `controller` y `service`.

---

## Configuración de base de datos

Edita el archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/empleados
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=none
```
---
## Migraciones con Liquibase

Liquibase está configurado para ejecutar automáticamente los cambios desde:

```
resources/db/changelog/db.changelog-master.yaml
```