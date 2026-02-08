# COLTUR - Módulo JDBC

Este proyecto demuestra la codificación del módulo COLTUR con operaciones CRUD usando JDBC y una base de datos SQLite.

## Requisitos
- Java 17
- Maven 3+

## Ejecución
1. Instalar dependencias y compilar:
   ```bash
   mvn clean package
   ```
2. Ejecutar la aplicación:
   ```bash
   mvn exec:java -Dexec.mainClass="com.coltur.app.ColturApplication"
   ```

La base de datos `coltur.db` se crea en el directorio raíz del proyecto.

## Estructura
- `com.coltur.app`: clase principal.
- `com.coltur.config`: conexión e inicialización de esquema.
- `com.coltur.dao`: operaciones CRUD.
- `com.coltur.model`: entidad del dominio.

El esquema SQL se encuentra en `src/main/resources/db/schema.sql`.
