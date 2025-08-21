# 📚 Literalura Challenge - Java con API REST

Este proyecto es una aplicación web desarrollada en Java con Spring Boot. Expone una API REST para consultar información sobre libros y autores, y cuenta con un front-end sencillo para interactuar con ella.

## 🚀 Arquitectura

- **Backend:** API REST construida con Spring Boot y Spring Web.
- **Persistencia:** Spring Data JPA con PostgreSQL.
- **Frontend:** Una página HTML con JavaScript que consume la API REST.

## 🌐 Endpoints de la API

- `GET /libros`
  - Devuelve una lista de todos los libros almacenados en la base de datos.

- `GET /autores/vivos?anio=<año>`
  - Devuelve una lista de autores que estaban vivos en el año especificado.

## 🛠 Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Web (API REST)
- Spring Data JPA
- PostgreSQL
- Maven
- HTML5 y JavaScript (para el front-end)

## 📂 Estructura del proyecto
```
src/
└── main/
    ├── java/
    │   └── com.kimjesus.literalura
    │       ├── controller/
    │       ├── model/
    │       ├── repository/
    │       ├── service/
    │       └── LiteraluraChallengeApplication.java
    └── resources/
        ├── static/
        │   └── index.html
        └── application.properties
```

## ▶️ Cómo ejecutar la aplicación

1.  Clona el repositorio:
    ```bash
    git clone https://github.com/KimJesus22/literalura-challenge-java.git
    cd literalura-challenge-java
    ```

2.  Abre el proyecto en tu IDE favorito (IntelliJ recomendado).

3.  Configura tu base de datos PostgreSQL y actualiza el archivo `src/main/resources/application.properties` con tus credenciales.

4.  Ejecuta la clase principal: `LiteraluraChallengeApplication.java`.

5.  ¡Listo! Abre tu navegador y ve a `http://localhost:8080` para interactuar con la aplicación.


👨‍💻 **Autor**

Desarrollado por Kim Jesus

- GitHub: @KimJesus22

¡Gracias por visitar el repositorio! ⭐
