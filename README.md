📚 Literatura App - Reto Alura Challenge
Aplicación de consola desarrollada en Java con Spring Boot que permite buscar, registrar y consultar libros y autores consumiendo la API pública Gutendex.
El proyecto sigue el patrón MVC (Modelo-Vista-Controlador) con una clara separación de capas.

🚀 Tecnologías y Herramientas
Java 17+

Spring Boot

Jackson Databind (para deserializar JSON)

PostgreSQL (base de datos relacional)

JPA / Hibernate (persistencia)

Gutendex API (fuente de datos)

🎯 Funcionalidades Disponibles (Menú)
🔎 Buscar libro por título:
Busca un libro desde la API Gutendex, lo registra en la base de datos si no existe y muestra su información.

📚 Listar libros registrados:
Muestra todos los libros almacenados en la base de datos.

👨‍💼 Listar autores registrados:
Muestra todos los autores guardados en la base de datos.

📅 Listar autores vivos en un año específico:
Permite consultar los autores que estaban vivos en un determinado año.

🌐 Listar libros por idioma:
Muestra todos los libros registrados en la base de datos según el idioma especificado.

📈 Top 5 libros con más descargas:
Lista los cinco libros más populares según el número de descargas.

🔍 Buscar autor por nombre:
Permite buscar autores por nombre, mostrando coincidencias parciales (búsqueda insensible a mayúsculas/minúsculas).

🏗️ Estructura del Proyecto
css
Copiar
Editar
src/
├── model         → Entidades de la base de datos (Libro, Autor)
├── repository    → Interfaces JPA para acceso a datos
├── service       → Lógica de negocio
├── principal     → Lógica del menú y entrada de usuario
├── LiteraturaApplication.java → Clase principal de Spring Boot
🗃️ Base de Datos
PostgreSQL

Configuración en application.properties

Uso de JPA/Hibernate para operaciones CRUD.

🔗 API Externa
Gutendex
Se utiliza para obtener datos de libros mediante peticiones REST.

📦 Instalación y Uso
Clona el repositorio:

bash
Copiar
Editar
git clone https://github.com/tu_usuario/tu_repositorio.git
Configura tu base de datos en src/main/resources/application.properties.

Ejecuta la aplicación:

bash
Copiar
Editar
./mvnw spring-boot:run
Interactúa con la app mediante el menú en la consola.

👨‍💻 Autor
Proyecto desarrollado como parte del Challenge Alura Latam.

