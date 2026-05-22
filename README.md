# Exception API

API REST construida con Spring Boot para gestionar usuarios y practicar respuestas estandarizadas, validaciones y manejo global de excepciones.

## Tecnologias

- Java 26
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- Spring Validation
- MySQL
- MapStruct
- Lombok
- Gradle
- Docker Compose

## Funcionalidades

- Crear usuarios.
- Listar todos los usuarios.
- Buscar un usuario por id.
- Actualizar un usuario por id.
- Eliminar un usuario por id.
- Validar campos de entrada con `jakarta.validation`.
- Responder errores con un `GlobalExceptionHandler`.
- Usar una respuesta generica `ApiResponse<T>` para respuestas exitosas y errores controlados.

## Estructura principal

```text
src/main/java/com/example/jose
├── controller
│   └── UserController.java
├── dto
│   ├── ApiResponse.java
│   └── UserResponse.java
├── exceptions
│   ├── GlobalExceptionHandler.java
│   └── UserNotFound.java
├── mapper
│   └── UserMapper.java
├── model
│   └── User.java
├── repository
│   └── UserRepository.java
└── service
    └── UserService.java
```

## Requisitos

- JDK 26
- Docker y Docker Compose, o una instancia local de MySQL

## Base de datos

El proyecto incluye un archivo `docker-compose.yaml` con MySQL 8.4.

Para iniciar la base de datos:

```bash
docker compose up -d
```

Configuracion usada por defecto:

```yaml
database: jose_db
username: jose
password: jose
port: 3306
```

## Ejecucion

Compilar y ejecutar pruebas:

```bash
./gradlew test
```

Levantar la aplicacion:

```bash
./gradlew bootRun
```

La API queda disponible por defecto en:

```text
http://localhost:8080
```

## Endpoints

### Crear usuario

```http
POST /users
Content-Type: application/json
```

```json
{
  "name": "Jose",
  "email": "jose@example.com",
  "password": "password123"
}
```

### Obtener usuarios

```http
GET /users
```

### Obtener usuario por id

```http
GET /users/{id}
```

### Actualizar usuario

```http
PUT /users/{id}
Content-Type: application/json
```

```json
{
  "name": "Jose Montes",
  "email": "jose.montes@example.com",
  "password": "newpass123"
}
```

### Eliminar usuario

```http
DELETE /users/{id}
```

## Validaciones

El DTO `UserResponse` valida:

- `name`: no puede estar vacio.
- `email`: no puede estar vacio y debe tener formato de correo valido.
- `password`: no puede estar vacio y debe tener minimo 8 caracteres.

Si falla una validacion, el `GlobalExceptionHandler` devuelve una respuesta `400 Bad Request` con los campos que tienen error.

## Manejo de excepciones

La excepcion personalizada `UserNotFound` se lanza cuando no existe un usuario con el id solicitado. El `GlobalExceptionHandler` la convierte en una respuesta `404 Not Found` usando el formato `ApiResponse`.

Ejemplo:

```json
{
  "status": "NOT_FOUND",
  "message": "User error",
  "body": "User not found"
}
```
