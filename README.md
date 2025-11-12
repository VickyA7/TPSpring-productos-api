# 🛍️ API REST de Gestión de Productos

## Autor
- Alumno: Victoria Acosta
- Legajo: 48897
- Curso: Desarrollo de Software - 3k9 - 2025
- Universidad Tecnologica Nacional

## 🧩 Descripción del Proyecto
Este proyecto consiste en el desarrollo de una **API REST completa** para la **gestión de productos** de un sistema de e-commerce.  
Forma parte del **Trabajo Práctico de la materia Programación III** de la **Tecnicatura Universitaria en Programación a Distancia (UTN)**.

El objetivo principal es aplicar los conceptos de arquitectura en capas, validaciones, manejo global de excepciones, persistencia con Spring Data JPA y documentación con Swagger/OpenAPI.

---

## ⚙️ Tecnologías Utilizadas
| Tecnología | Descripción |
|-------------|--------------|
| **Java 17+** | Lenguaje de programación principal |
| **Spring Boot 3.x** | Framework para el desarrollo de la API REST |
| **Spring Web** | Creación de controladores REST |
| **Spring Data JPA** | Persistencia de datos y operaciones CRUD |
| **H2 Database** | Base de datos en memoria para pruebas |
| **Lombok** | Simplificación del código (getters, setters, constructores, etc.) |
| **Validation (Jakarta Bean Validation)** | Validación de datos con anotaciones |
| **Spring Boot DevTools** | Recarga automática durante el desarrollo |
| **Swagger / Springdoc OpenAPI** | Documentación interactiva de la API |

---

## 🚀 Instrucciones para Ejecutar el Proyecto

### 1️⃣ Clonar el repositorio
```bash
git clone https://github.com/VickyA7/TPSpring-productos-api.git
cd productos-api
```
### 2️⃣  Compilar y ejecutar el proyecto
```bash
./mvnw clean install
./mvnw spring-boot:run 
```
### En Windows:
```bash
mvnw.cmd clean install
mvnw.cmd spring-boot:run 
```
### 3️⃣ Verificar que la aplicación está corriendo
La aplicación estará disponible en: http://localhost:8080

## TABLA DE ENDPOINTS

| Método     | Ruta                                   | Descripción                             | Cuerpo esperado      |
| ---------- | -------------------------------------- | --------------------------------------- | -------------------- |
| **GET**    | `/api/productos`                       | Listar todos los productos              | —                    |
| **GET**    | `/api/productos/{id}`                  | Obtener un producto por su ID           | —                    |
| **GET**    | `/api/productos/categoria/{categoria}` | Listar productos por categoría          | —                    |
| **POST**   | `/api/productos`                       | Crear un nuevo producto                 | `ProductoDTO`        |
| **PUT**    | `/api/productos/{id}`                  | Actualizar un producto completo         | `ProductoDTO`        |
| **PATCH**  | `/api/productos/{id}/stock`            | Actualizar solo el stock de un producto | `ActualizarStockDTO` |
| **DELETE** | `/api/productos/{id}`                  | Eliminar un producto                    | —                    |

## 📸 Capturas de Pantalla
### 1. Documentación de Swagger UI
<img width="1087" height="832" alt="image" src="https://github.com/user-attachments/assets/9339be99-cc5f-48ce-b1c0-bcb433edf369" />

