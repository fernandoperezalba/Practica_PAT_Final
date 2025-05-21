# Web Adaptada a Personas Mayores

> **Proyecto PAT – Práctica Final**  
> Fernando Perez Alba (201806570) – 5º GITT+BA


#### URL render: https://practica-pat-final.onrender.com

---

## Primeros pasos

Para probar la web hay que registrarse inicialmente ya que no hay usuarios. Se debe introducir un usuario y un ayudante. 
Abre el navegador en `http://localhost:8080` y registra **un nuevo usuario abuelo** y **un nuevo ayudante**.

---

## Introducción

Esta aplicación tiene como objetivo acercar el mundo digital a las personas mayores mediante una interfaz simplificada de grandes botones y texto ampliado.  
Cada cuenta tiene dos roles:
- **Abuelo/a** – vista sencilla centrada en entretenimiento y organización.
- **Ayudante** – familiar o cuidador que configura la información del abuelo.

Las funcionalidades incluyen previsión meteorológica, calendario, recetas, documentales y herramientas ligeras de ocio.

---

## Planificación

El proyecto se construyó en **cinco iteraciones** progresivas:
1. **Fundamentos** – se crearon las entidades principales (*User*, *Abuelo*, *Ayudante*) y la autenticación JWT con sus primeras pruebas de validación de DTO (`JwtRequestTest`).
2. **Servicios de dominio** – se implementaron los servicios y repositorios con Spring Data, acompañados de tests de base de datos (`AbueloRepositoryTest`, `AyudanteRepositoryTest`) y lógica de negocio (`AbueloServiceTest`).
3. **Capa web** – se expuso la API REST y se añadieron pruebas de controlador con MockMvc (`AbueloControllerTest`, `AyudanteControllerTest`).
4. **Módulos funcionales** – calendario, clima y recetas, asegurando cobertura suficiente.
5. **Pulido y accesibilidad** – ajustes de UX (alto contraste, fuentes), hardening de seguridad y empaquetado Docker.

Cada iteración incluía código, pruebas automáticas y una pequeña demo para los stakeholders.

---

## Arquitectura & Stack

La solución sigue una arquitectura clásica **backend-centrada**:
- **Java 17 + Spring Boot 3** para la API REST y la seguridad (Spring Security + JWT).
- **Spring Data JPA** sobre **PostgreSQL** para persistencia.
- **Lombok** para reducir boilerplate y **Maven** como sistema de construcción.
- Contenedorización con **Docker** y orquestación vía **Docker Compose**.

> El frontend ligero contenido en static consume los endpoints REST y forma parte de este repositorio.

---

## API REST

### Autenticación y usuarios
- `POST /api/login` – autentica y devuelve un token JWT.
- `POST /api/users/new-user` – registra un nuevo usuario (abuelo + ayudante).
- `POST /api/users/update-password` – cambia la contraseña.
- `GET  /api/users/user-id?username=` – obtiene el **id** a partir del *username*.
- `GET  /api/users/isAbuelo?id=` – indica si un usuario es abuelo.
- `GET  /api/users/isAyudante?id=` – indica si un usuario es ayudante.

### Abuelos
- `POST /api/abuelos/new-abuelo` – alta de abuelo.
- `GET  /api/abuelos` – lista de abuelos.
- Otros accesos de ocio: `/Noticias`, `/VerAgenda`, `/Juegos`, `/Ejercicio`, `/Futbol`, `/VideosPersonalizados`, `/Religion`, `/Moda`, `/Mapa/Ver`, `/Radio`, `/Tiempo`, `/InfoPublica`, `/Chat/Chatear`, y endpoints CRUD de `ListaCompra`.

### Ayudantes
- `POST /api/ayudantes/new-ayudante` – alta de ayudante.
- `GET  /api/ayudantes` – lista de ayudantes.
- Endpoints de administración del calendario (`/Agenda/*`), datos del abuelo (`/Datos`, `/VerPerfil`), lista de la compra, mapas y supervisión de chat.

Errores globales gestionados por `CAController` devolviendo códigos HTTP estándar **401** y **500**.

---

## Modelo de datos

Las entidades principales son:
- **User**: `id`, `username`, `password`, `role`.
- **Abuelo**: `id`, `nombre`, `apellidos`, `fechaNacimiento`.
- **Ayudante**: `id`, `nombre`, `apellidos`, `correo`, `id_abuelo`.
- **Calendario**: `id`, `titulo`, `fecha`, `id_abuelo`.
- **ListaCompra**: `id`, `producto`, `cantidad`, `checked`, `id_abuelo`.
- **ChatMensaje**: `id`, `texto`, `fecha`, `emisorId`, `receptorId`.

Relaciones destacadas: un abuelo puede tener varios ayudantes (1-n), eventos de calendario (1-n) y elementos de lista de la compra (1-n).

---

## Pruebas & Calidad

- **Cobertura actual**: 80 % de líneas (Jacoco).
- **Capas cubiertas**: modelo (validaciones), repositorio, servicio, controlador y DTOs.
- **Suites destacadas**:
    - `JwtRequestTest` – valida restricciones de los datos de autenticación.
    - `AbueloRepositoryTest` & `AyudanteRepositoryTest` – persiste y recupera entidades.
    - `AbueloServiceTest` & `AyudanteServiceTest` – integra capa de servicio y repositorio.
    - `AbueloControllerTest` & `AyudanteControllerTest` – pruebas de la API con MockMvc.
- **CI**: los tests se ejecutan en cada *pull request* y fallan el pipeline si algo no compila o baja la cobertura.

---


## Licencia

MIT © 2025 Fernando Perez Alba


