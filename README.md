# 📚 Literalura Challenge - Java

Este proyecto es una aplicación de consola desarrollada en Java con Spring Boot que permite explorar y almacenar información de libros y autores usando la API de [Gutendex](https://gutendex.com/), una fuente de libros del Proyecto Gutenberg.

## 🚀 Funcionalidades principales

- 🔍 Buscar libros por título desde la API y guardarlos localmente
- 📚 Ver historial de libros guardados
- 🧑‍🎓 Listar autores registrados
- 📅 Buscar autores que estaban vivos en un año específico
- 🌍 Ver cantidad de libros por idioma
- 📈 Mostrar estadísticas de descargas de libros
- 🏆 Top 10 libros más descargados
- 🔎 Buscar autores por nombre (y ver sus libros)

## 🛠 Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database (modo memoria)
- Lombok (opcional)
- API REST (Gutendex)
- Maven

## 📂 Estructura del proyecto
src/
└── main/
├── java/
│ └── com.kimjesus.literalura
│ ├── model/
│ ├── repository/
│ ├── service/
│ ├── principal/
│ └── LiteraluraChallengeApplication.java
└── resources/
└── application.properties

## ▶️ Cómo ejecutar la aplicación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/KimJesus22/literalura-challenge-java.git
   cd literalura-challenge-java
   
   2.Abre el proyecto en tu IDE favorito (IntelliJ recomendado)

3.Ejecuta la clase:LiteraluraChallengeApplication.java
4.¡Listo! Interactúa con el menú desde la consola.

📌 Notas
La base de datos se reinicia en cada ejecución (modo en memoria).

Todos los datos se almacenan localmente usando JPA/Hibernate.

Puedes extender la app para usar una base de datos persistente como PostgreSQL o MySQL
👨‍💻 Autor
Desarrollado por Kim Jesus
GitHub: @KimJesus22
¡Gracias por visitar el repositorio! ⭐

