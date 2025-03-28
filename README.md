# BackendAlianza_FO

Este proyecto fue desarrollado como parte de la prueba técnica de la empresa GML Software por Fabián Ojeda.

## Descripción

BackendAlianza_FO es una API REST construida con Java 17, Spring Boot y gestionada mediante Maven. Está diseñada para la gestión de clientes, ofreciendo las siguientes funcionalidades:

- Obtener todos los clientes registrados.
- Buscar clientes cuyo `sharedKey` contenga un valor parcial proporcionado por el usuario (Esta implementación responde a la interpretación personal de el requerimiento de este item de la prueba)
- Crear nuevos clientes.
- Insertar algunos datos iniciales en la base de datos al momento de arrancar, para no iniciar con una base de datos vacía.



## Tecnologías utilizadas

- Java 17
- Spring Boot
- Maven
- Base de datos H2 (modo local, embebida)
- Librerías para desarrollo web, documentación y testing

## Flujo para poner en funcionamiento

1. Clonar el proyecto en tu equipo.
2. Verificar que tengas Java 17 y Maven correctamente instalados.
3. Ubicarte en la raíz del proyecto y ejecutar:

   mvn clean compile
   mvn spring-boot:run

4. Una vez iniciada, la aplicación quedará disponible en:

   http://localhost:8081/backendAlianza

## Endpoints principales

El controlador de clientes están disponibles bajo la ruta base `/backendAlianza/clients` y soporta:

- GET /getAllClients — Obtener todos los clientes.
- GET /getClientsBySharedKey/{sharedKey} — Buscar clientes por coincidencia parcial en sharedKey.
- POST /create — Crear un nuevo cliente (requiere JSON en el body con los datos del cliente).

La API utiliza parámetros en URL y cuerpos JSON según corresponda a cada operación.

## Observaciones

- La aplicación inicializa con algunos registros predeterminados.
- Al estar en modo desarrollo, el backend tiene configuración de CORS que permite consumo sdesde cualquir origen

## Testing

- La aplicación cuenta con el testing considerado necesario por el desarrollador

## Logs

- Se han implementado logs para el seguimiento de operaciones

## Contacto
Fabián Ojeda
Email: fabian1997ojeda@gmail.com