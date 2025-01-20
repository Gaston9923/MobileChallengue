
# Mobile Challenge - Ualá - README

Esta aplicación permite buscar y visualizar ciudades desde un archivo JSON de más de 200k entradas. Los usuarios pueden:
- Filtrar ciudades por prefijo.
- Marcar ciudades como favoritas.
- Visualizar la ubicación de las ciudades en un mapa.
- Acceder a información detallada de cada ciudad.

## Enfoque para Resolver el Problema

Búsqueda Eficiente:
Para optimizar la búsqueda en un archivo JSON extenso y fijo, se empleó un enfoque basado en el uso de un Map, lo que permite realizar búsquedas rápidas por prefijo sin comprometer la memoria de la aplicación. Este enfoque es especialmente adecuado dado que el archivo JSON es estático y no cambia a lo largo del tiempo.
Gracias a esta implementación, la actualización de la lista de resultados es ágil y sensible, incluso a medida que el usuario interactúa con el filtro, lo que mejora significativamente la experiencia del usuario y mantiene un rendimiento eficiente, incluso con grandes volúmenes de datos.

Persistencia de Favoritos:
Los favoritos se almacenan usando SharedPreferences, una solución moderna para persistencia de datos en Android. Esto asegura que los datos de favoritos sean seguros y persistentes entre sesiones.

Diseño dinámico y adaptable:  
Toda la interfaz fue desarrollada usando Jetpack Compose, siguiendo las mejores prácticas de diseño declarativo en Android. Esto permite una construcción rápida y flexible de la UI.
La interfaz se adapta automáticamente al modo vertical u horizontal:
   - Vertical: Se utilizan pantallas separadas para la lista de ciudades y el mapa.
   - Horizontal: Se muestra una sola pantalla dividida con la lista y el mapa.

## Decisiones Importantes

1. Estructura de los datos y optimización de búsqueda:
Se decidió utilizar un Map para gestionar los datos provenientes del JSON debido a la naturaleza estática del endpoint y a que los datos no cambian durante la ejecución de la aplicación. Esta estructura permitió realizar búsquedas de manera eficiente sin necesidad de estructuras más complejas, optimizando tanto el tiempo de desarrollo como el rendimiento en tiempo de ejecución.
2. Interfaz de usuario responsiva:
Para la implementación de la interfaz se utilizó Jetpack Compose, que permitió crear un diseño moderno y altamente responsivo. La lista de resultados de ciudades se actualiza en tiempo real utilizando StateFlow, asegurando que la experiencia del usuario sea fluida y reactiva al filtrar resultados.
3. Persistencia de favoritos:
Se implementó el almacenamiento de ciudades marcadas como favoritas utilizando SharedPreferences, lo que permitió una solución sencilla y eficiente para recordar los favoritos entre los reinicios de la aplicación sin agregar complejidad innecesaria.
4. Gestión del modo de pantalla (vertical y horizontal):
Se manejó el diseño dinámico dependiendo de la orientación del dispositivo utilizando LocalConfiguration. En modo vertical, se usó un sistema de navegación con NavHost, mientras que en modo horizontal, ambas vistas (lista y mapa) se muestran simultáneamente utilizando un layout de tipo Row. Este enfoque garantiza una experiencia de usuario coherente y adaptada al contexto.
5. Restricciones técnicas
Se evitaron versiones beta o antiguas de librerías, SDKs y herramientas, garantizando la estabilidad y compatibilidad de la aplicación con las últimas versiones de Android y Jetpack Compose.
Estas decisiones permitieron cumplir con los requisitos del proyecto mientras se optimizó el desarrollo y la experiencia del usuario final.

## Cómo Ejecutar y Probar la Aplicación
Requisitos Previos:
1. Android Studio Flamingo (o superior).
2. Dispositivo o emulador con Android API 21 o superior.

Pasos:
1. Clona el repositorio: git clone <URL_DEL_REPOSITORIO>
2. Abre el proyecto en Android Studio.
3. Sincroniza las dependencias de Gradle.
4. Ejecuta la aplicación en un emulador o dispositivo físico.

### Pruebas
Se implementaron pruebas unitarias para verificar el correcto funcionamiento de las búsquedas y la visualización.
- Correcta funcionalidad del algoritmo de búsqueda.
- Manejo de entradas válidas e inválidas.

Pruebas de UI:
- Validación de la navegación entre pantallas.
- Comprobación de la persistencia de favoritos.

## Licencia
Este proyecto es solo para fines de evaluación y no debe ser utilizado con fines comerciales.

