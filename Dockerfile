# Etapa 1: Construir el frontend de React
FROM node:18 AS frontend
WORKDIR /app/frontend
COPY frontend/package.json frontend/package-lock.json ./
RUN npm install
COPY frontend/ .
RUN npm run build

# Etapa 2: Construir el backend de Spring Boot
FROM eclipse-temurin:17-jdk AS backend
WORKDIR /app

# Copiar el proyecto de backend
COPY . .

# Copiar el frontend construido desde la etapa anterior
COPY --from=frontend /app/frontend/dist /app/src/main/resources/static

# Dar permisos de ejecución al wrapper de Maven
RUN chmod +x ./mvnw

# Compilar el proyecto (usando wrapper)
RUN ./mvnw package -Dmaven.test.skip=true

# Exponer el puerto que Spring Boot usa por defecto
EXPOSE 8080

# Ejecutar la app
CMD ["java", "-jar", "target/Literalura-0.0.1-SNAPSHOT.jar"]
