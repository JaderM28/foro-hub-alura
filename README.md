

# Foro Hub Alura Latam

---

## Descripción

Este proyecto es una API REST que proporciona funcionalidades CRUD básicas para gestionar tópicos en una base de datos PostgreSQL. Utiliza Spring Boot Security para autenticación mediante token.

## Características

- API RESTful con las operaciones CRUD básicas.
- Permite el registro y gestión de tópicos en una base de datos PostgreSQL.
- Integración con Spring Boot Security para autenticación mediante token.

## Requisitos

- Java 8 o superior.
- Conexión a internet para la actualización de tasas de cambio (no mencionado en la descripción original, pero ajustado para la coherencia del documento).

## Instalación

1. Clona este repositorio en tu máquina local:
    ```bash
    git clone https://github.com/JaderM28/foro-hub-alura.git
    ```

2. Navega al directorio del proyecto:
    ```bash
    cd foro-hub-alura
    ```

## Uso

1. Inicia la aplicación para que escuche en el puerto 8080.
2. Utiliza herramientas como Postman para interactuar con la API.
3. La aplicación requiere autenticación para acceder a las funcionalidades protegidas.
4. Puedes realizar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los tópicos.

---

## Rutas


1. Listar Topicos (GET):
    ```bash
    http://localhost:8080/topicos
    ```

2. Registrar Topicos (POST):
    ```bash
    http://localhost:8080/topicos
    ```
3. Actualizar Topicos (PUT):
    ```bash
    http://localhost:8080/topicos/{id}
    ```
4. Eliminar Topicos (DELETE):
    ```bash
    http://localhost:8080/topicos/{id}
    ```
5. Autenticacion (POST):
    ```bash
    http://localhost:8080/auth
    ```
6. Listar Topico Especifico (GET):
    ```bash
    http://localhost:8080/topicos/{id}
    ```


By Jader Montoya Montoya

---
