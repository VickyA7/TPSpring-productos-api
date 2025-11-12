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
<img width="407" height="301" alt="image" src="https://github.com/user-attachments/assets/9339be99-cc5f-48ce-b1c0-bcb433edf369" />

### 2. POST - Code: 201 Created
<img width="407" height="301" alt="image" src="https://github.com/user-attachments/assets/40be1744-8fe8-439e-8615-fcd30cc951d7" />

### 3. GET - Code: 200 OK
<img width="407" height="301" alt="image" src="https://github.com/user-attachments/assets/c04fb499-f757-451e-9149-c67ddbc69f7d" />

### 4. GET Error - Code: 404 Not Found
<img width="407" height="301" alt="image" src="https://github.com/user-attachments/assets/2bbf6284-c3be-49ca-9794-716a6ff2bb13" />

### 5. POST - Code: 400 Bad Request
PRODUCTO SIN NOMBRE
<img width="407" height="301" alt="image" src="https://github.com/user-attachments/assets/daea8f04-be3d-4f4d-abb1-324746b80e48" />

---
PRODUCTO CON PRECIO NEGATIVO
<img width="407" height="301" alt="image" src="https://github.com/user-attachments/assets/44c23319-3019-4359-881d-20c2fa934afc" />

---
PRODUCTO CON STOCK NEGATIVO
<img width="407" height="301" alt="image" src="https://github.com/user-attachments/assets/3990ab07-ffcc-459c-a26b-cf5ea18d362d" />


### 6. Consola H2 - Datos persistidos
<img width="409" height="301" alt="image" src="https://github.com/user-attachments/assets/786af72c-19ca-4878-8957-25d2b0629e75" />

## 🏗️ Arquitectura del Proyecto
```
com.utn.productos
├── controller/          # Controladores REST
├── dto/                 # Data Transfer Objects
├── exception/           # Manejo de excepciones personalizadas
├── model/               # Entidades JPA
├── repository/          # Repositorios de Spring Data JPA
└── service/             # Lógica de negocio
```

## 🧪Pruebas Realizadas
### Casos de éxito:
- ✅ Creación de productos con todos los campos válidos
- ✅ Listado completo de productos
- ✅ Filtrado por categoría
- ✅ Obtención de producto por ID
- ✅ Actualización completa de producto (PUT)
- ✅ Actualización parcial de stock (PATCH)
- ✅ Eliminación de producto (DELETE)
#### Casos de error:
- ✅ Validación: producto sin nombre (400)
- ✅ Validación: precio negativo (400)
- ✅ Validación: stock negativo (400)
- ✅ Producto no encontrado (404)
- ✅ Errores internos del servidor (500)

## 💭 Conclusiones Personales
Este trabajo práctico me permitió consolidar los conocimientos sobre el desarrollo de APIs REST profesionales con Spring Boot. Los aprendizajes más significativos fueron:

1. Arquitectura en Capas: Comprendí la importancia de separar responsabilidades para lograr un código más mantenible y escalable. La separación entre Controllers, Services y Repositories hace que cada componente tenga una función clara y bien definida.
2. Documentacion con Swagger: Swagger/OpenAPI no solo genera documentación automática, sino que proporciona una interfaz interactiva que facilita las pruebas y mejora la comunicación entre equipos.
3. Buenas Prácticas REST: Aprendí a utilizar correctamente los métodos HTTP (GET, POST, PUT, PATCH, DELETE) y los códigos de estado apropiados, lo cual es fundamental para diseñar APIs que sigan los estándares de la industria.

Este proyecto representa una base sólida para desarrollar aplicaciones empresariales reales y me ha dado las herramientas para enfrentar desafíos más complejos en el futuro.

## 📝 Licencia
Este proyecto fue desarrollado con fines académicos como parte del trabajo práctico de Desarrollo de Software.
