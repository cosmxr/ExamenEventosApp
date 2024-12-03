ExamenEventosApp
Descripción
ExamenEventosApp es una aplicación móvil desarrollada en Kotlin utilizando Jetpack Compose. La aplicación permite a los usuarios gestionar su horario de asignaturas, incluyendo la creación, visualización y consulta de la asignatura actual.  
Funcionalidades
Pantalla Principal:  
Muestra botones para agregar asignaturas, ver el horario y consultar la asignatura actual.
Agregar Asignatura:  
Permite a los usuarios añadir una nueva asignatura proporcionando el nombre, día, hora de inicio y hora de finalización.
Valida que todos los campos estén completos antes de guardar la asignatura.
Ver Horario:  
Permite a los usuarios seleccionar un día y ver las asignaturas programadas para ese día.
Asignatura Actual:  
Muestra la asignatura que está en curso en el momento actual o un mensaje indicando que no hay asignaturas en curso.
Estructura del Código
MainActivity.kt
MainActivity: La actividad principal que configura la navegación y la barra superior.
MainScreen: Composable que muestra los botones de navegación para agregar asignaturas, ver el horario y consultar la asignatura actual.
AddScheduleScreen.kt
AddScheduleScreen: Composable que permite a los usuarios añadir una nueva asignatura. Incluye campos de selección para la asignatura, día, hora de inicio y hora de finalización, y un botón para guardar.
ViewScheduleScreen.kt
ViewScheduleScreen: Composable que permite a los usuarios seleccionar un día y ver las asignaturas programadas para ese día.
CurrentSubjectScreen.kt
CurrentSubjectScreen: Composable que muestra la asignatura en curso o un mensaje indicando que no hay asignaturas en curso.
CurrentSubjectHelper.kt
CurrentSubjectHelper: Clase que ayuda a determinar la asignatura en curso basándose en el horario y la hora actual.
Theme.kt
ExameneventosappTheme: Configura el tema de la aplicación, incluyendo esquemas de colores para modos claro y oscuro.
Dependencias
Jetpack Compose
Navigation Compose
Material3
Firebase (Analytics, Firestore, Auth, Database)
Instalación
Clona el repositorio.
git clone https://github.com/cosmxr/ExamenEventosApp.git
Abre el proyecto en Android Studio.
Configura Firebase en tu proyecto:
Ve a la consola de Firebase y crea un nuevo proyecto.
Añade la aplicación de Android a tu proyecto de Firebase.
Descarga el archivo google-services.json y colócalo en el directorio app de tu proyecto.
Añade las dependencias de Firebase en tu archivo build.gradle.
Sincroniza las dependencias de Gradle.
Ejecuta la aplicación en un dispositivo o emulador Android.
Uso
Abre la aplicación.
En la pantalla principal, añade nuevas asignaturas utilizando el botón de agregar.
Visualiza el horario de asignaturas desde la pantalla de ver horario.
Consulta la asignatura en curso desde la pantalla de asignatura actual.
Link al repositorio
