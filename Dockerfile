# Imagen base de Java 17
FROM eclipse-temurin:17-jdk

# Crear directorio de trabajo
WORKDIR /app

# Copiar el proyecto
COPY . .

# Dar permisos de ejecución al wrapper de Maven
RUN chmod +x ./mvnw

# Compilar el proyecto (usando wrapper)
RUN ./mvnw package -DskipTests

# Exponer el puerto que Spring Boot usa por defecto
EXPOSE 8080

# Ejecutar la app
CMD ["java", "-jar", "target/Literalura-0.0.1-SNAPSHOT.jar"]


