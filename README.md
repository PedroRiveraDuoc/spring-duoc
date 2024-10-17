# SecureRecipeApp

## Descripción
SecureRecipeApp es una aplicación web diseñada para gestionar y compartir recetas de cocina de manera segura. Construida con Spring Boot, incorpora Spring Security para la autenticación y autorización de usuarios, garantizando que solo los usuarios registrados puedan acceder y gestionar las recetas. El frontend está desarrollado con Thymeleaf, HTML y CSS, proporcionando una interfaz de usuario moderna y receptiva.

Este proyecto sirve como una demostración del uso efectivo de tecnologías Java y un diseño de interfaz simple para crear una experiencia web segura y atractiva.

## Características
- **Autenticación de Usuarios**: Funcionalidad segura de inicio de sesión y registro utilizando Spring Security.
- **Gestión de Recetas**: Los usuarios pueden ver, crear y gestionar sus recetas.
- **Interfaz Receptiva**: Desarrollada con un diseño limpio e intuitivo usando HTML, CSS y Thymeleaf.
- **Seguridad**: Control de acceso basado en roles para asegurar que solo los usuarios autorizados puedan realizar cambios.

## Tecnología Utilizada
- **Backend**: Java, Spring Boot, Spring Security
- **Frontend**: Thymeleaf, HTML, CSS
- **Herramienta de Construcción**: Maven
- **Base de Datos**: H2 (base de datos en memoria para propósitos de demostración)

## Requisitos Previos
- **Java**: Versión 17 o superior
- **Maven**: Versión 3.6+ para construir y ejecutar el proyecto

## Cómo Empezar
### Clonar el Repositorio
```bash
[https://github.com/yourusername/SecureRecipeApp](https://github.com/PedroRiveraDuoc/spring-duoc).git
cd spring-duoc
```

### Construir y Ejecutar
1. **Construir el Proyecto**
   ```bash
   mvn clean install
   ```

2. **Ejecutar la Aplicación**
   ```bash
   mvn spring-boot:run
   ```

### Acceder a la Aplicación
- Abra un navegador web y navegue a [http://localhost:8080](http://localhost:8080).

## Uso
1. **Regístrese** como un nuevo usuario para crear una cuenta.
2. **Inicie sesión** con sus credenciales.
3. Una vez que haya iniciado sesión, puede agregar, editar o eliminar recetas.

## Estructura del Proyecto
- `src/main/java`: Contiene todo el código fuente Java (controladores, servicios, configuración).
- `src/main/resources`: Contiene las propiedades de la aplicación, recursos estáticos (CSS) y plantillas (vistas HTML).

---

¡Proyecto en progreso - fase inicial!

