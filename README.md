# 📚✨ Literalura - Explorador de Libros

¡Bienvenido a Literalura! Una aplicación web full-stack moderna para explorar el vasto mundo de los libros a través de la API de Gutendex.

## 📖 Descripción

Literalura es una aplicación robusta que permite a los usuarios buscar información sobre libros y autores. Construida con una arquitectura desacoplada, utiliza un backend de **Spring Boot** para servir una API RESTful y un frontend de **React** para proporcionar una experiencia de usuario interactiva y dinámica.

La aplicación está completamente dockerizada, permitiendo un despliegue sencillo y consistente en cualquier entorno.

---

## 🚀 Características Principales

*   **Interfaz Moderna con React**: Una Single-Page Application (SPA) rápida y responsiva.
*   **Búsqueda de Libros**: Encuentra y guarda libros de la API de Gutendex en la base de datos local.
*   **Visualización de Datos**:
    *   Explora todos los libros guardados.
    *   Descubre el **Top 10** de libros más descargados.
    *   Filtra la colección por **idioma**.
    *   Busca autores que estaban **vivos en un año específico**.
*   **Estadísticas Visuales**: Muestra el número total de libros por idioma a través de tarjetas interactivas.
*   **Tema Claro y Oscuro**: Selector de tema que persiste la preferencia del usuario.
*   **Documentación de la API**: Acceso directo a la documentación de la API generada con Swagger/SpringDoc.

---

## 🛠️ Arquitectura y Tecnologías

Este proyecto sigue las mejores prácticas de desarrollo, con una clara separación entre el backend y el frontend.

**Backend:**
*   **Java 17** y **Spring Boot 3**
*   **Spring Web**: para la creación de la API REST.
*   **Spring Data JPA**: para la persistencia de datos.
*   **PostgreSQL**: como base de datos relacional.
*   **Maven**: para la gestión de dependencias.
*   **SpringDoc (Swagger)**: para la documentación automática de la API.
*   **Manejo de Errores Global**: con `@ControllerAdvice` para respuestas de error consistentes.

**Frontend:**
*   **React 18** (con Hooks)
*   **Vite**: como herramienta de construcción y servidor de desarrollo.
*   **React Router DOM**: para el enrutamiento del lado del cliente.

**Despliegue:**
*   **Docker**: Contenerización completa de la aplicación.
*   **Build Multi-Etapa**: El `Dockerfile` construye el frontend y luego lo empaqueta junto con el backend de Spring Boot en una única imagen optimizada.

---

## ▶️ Cómo Ejecutar

### Opción 1: Ejecutar con Docker (Recomendado)

Esta es la forma más sencilla de poner en marcha toda la aplicación.

1.  **Requisitos**: Tener Docker instalado y en ejecución.
2.  **Construir la imagen de Docker**:
    ```bash
    docker build -t literalura .
    ```
3.  **Ejecutar el contenedor**:
    ```bash
    docker run -p 8080:8080 -e DB_URL="jdbc:postgresql://<tu_host_db>:5432/<tu_db>" -e DB_USERNAME="<tu_usuario>" -e DB_PASSWORD="<tu_contraseña>" literalura
    ```
    > **Nota**: Reemplaza los placeholders con tus credenciales de PostgreSQL. La base de datos debe ser accesible desde donde se ejecuta Docker.

    La aplicación estará disponible en `http://localhost:8080`.

### Opción 2: Ejecutar Localmente para Desarrollo

Asegúrate de tener instalado:
*   Java JDK 17+
*   Maven 3.8+
*   Node.js 18+ (incluye npm)
*   Una instancia de PostgreSQL en ejecución.

#### 1. Backend (API)

a. **Configura la Base de Datos**:
El proyecto está configurado para usar variables de entorno. Para desarrollo local, puedes confiar en los valores por defecto en `src/main/resources/application.properties` o establecer las variables `DB_URL`, `DB_USERNAME`, y `DB_PASSWORD` en tu sistema. Asegúrate de que la base de datos `literalura` exista.

b. **Ejecuta la Aplicación**:
Desde tu IDE, ejecuta la clase `LiteraluraChallengeApplication.java`, o usa Maven desde la raíz del proyecto:
```bash
./mvnw spring-boot:run
```
El backend estará disponible en `http://localhost:8080`.

#### 2. Frontend (Interfaz de Usuario)

a. **Navega a la Carpeta del Frontend**:
En una **nueva terminal**, ve a la carpeta `frontend`.
```bash
cd frontend
```

b. **Instala las Dependencias**:
```bash
npm install
```

c. **Ejecuta la Aplicación de React**:
```bash
npm run dev
```
La aplicación se abrirá en `http://localhost:5173` y se conectará automáticamente al backend a través del proxy configurado en Vite.

---

### 📖 Documentación de la API

Una vez que el backend esté en ejecución, puedes acceder a la documentación interactiva de la API (Swagger UI) en la siguiente URL:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

### 👨‍💻 Autor

Desarrollado por Kim Jesus

*   GitHub: [@KimJesus22](https://github.com/KimJesus22)

¡Gracias por visitar el repositorio! ⭐
