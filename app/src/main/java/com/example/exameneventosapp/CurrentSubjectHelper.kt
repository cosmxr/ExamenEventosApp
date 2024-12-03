import android.content.Context
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

//clase para mostrar la asignatura actual
class CurrentSubjectHelper(private val context: Context, private val navController: NavController) {
    fun showCurrentSubject(schedule: Map<String, Map<String, Pair<String, String>>>) {
        val calendar = Calendar.getInstance()
        val currentDay = getDayOfWeek(calendar)
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)

        val daySchedule = schedule[currentDay]

        //busca la asignatura actual
        val currentSubject = daySchedule?.entries?.find { entry ->
            val startTime = entry.key
            val endTime = entry.value.first
            currentTime in startTime..endTime
        }?.value?.second

        navController.navigate("currentSubject/${currentSubject ?: "No hay asignaturas en curso en este momento."}")
    }

    //obtiene el dia de la semana en un formato legible para esta app
    private fun getDayOfWeek(calendar: Calendar): String {
        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> "Lunes"
            Calendar.TUESDAY -> "Martes"
            Calendar.WEDNESDAY -> "Miercoles"
            Calendar.THURSDAY -> "Jueves"
            Calendar.FRIDAY -> "Viernes"
            else -> "No lectivo"
        }
    }
}