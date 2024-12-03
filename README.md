# ExamenEventosApp  

## Descripción  
**ExamenEventosApp** es una aplicación móvil desarrollada en Kotlin utilizando **Jetpack Compose**. La aplicación permite a los usuarios gestionar su horario de asignaturas, incluyendo la creación, visualización y consulta de la asignatura actual.  

---

## Funcionalidades  

### Pantalla Principal  
- Muestra botones para agregar asignaturas, ver el horario y consultar la asignatura actual.  

### Agregar Asignatura  
- Permite a los usuarios añadir una nueva asignatura proporcionando el nombre, día, hora de inicio y hora de finalización.  
- Valida que todos los campos estén completos antes de guardar la asignatura.  

### Ver Horario  
- Permite a los usuarios seleccionar un día y ver las asignaturas programadas para ese día.  

### Asignatura Actual  
- Muestra la asignatura que está en curso en el momento actual.  
- Si no hay asignaturas en curso, muestra un mensaje indicándolo.  

---

## Estructura del Código  

### **`MainActivity.kt`**  
- **`MainActivity`**: La actividad principal que configura la navegación y la barra superior.  
- **`MainScreen`**: Composable que muestra los botones de navegación para agregar asignaturas, ver el horario y consultar la asignatura actual.  

### **`AddScheduleScreen.kt`**  
- **`AddScheduleScreen`**:  
  - Composable que permite a los usuarios añadir una nueva asignatura.  
  - Incluye campos de selección para la asignatura, día, hora de inicio y hora de finalización, y un botón para guardar.  

### **`ViewScheduleScreen.kt`**  
- **`ViewScheduleScreen`**: Composable que permite a los usuarios seleccionar un día y ver las asignaturas programadas para ese día.  

### **`CurrentSubjectScreen.kt`**  
- **`CurrentSubjectScreen`**:  
  - Composable que muestra la asignatura en curso.  
  - Muestra un mensaje indicando que no hay asignaturas en curso si aplica.  

### **`CurrentSubjectHelper.kt`**  
- **`CurrentSubjectHelper`**: Clase que ayuda a determinar la asignatura en curso basándose en el horario y la hora actual.  

### **`Theme.kt`**  
- **`ExameneventosappTheme`**: Configura el tema de la aplicación, incluyendo esquemas de colores para modos claro y oscuro.  

---

## Dependencias  
- **Jetpack Compose**  
- **Navigation Compose**  
- **Material3**  
- **Firebase**:  
  - Analytics  
  - Firestore  
  - Auth  
  - Database  

---

## Instalación  

1. Clona el repositorio:  
   ```bash
   git clone https://github.com/cosmxr/ExamenEventosApp.git
