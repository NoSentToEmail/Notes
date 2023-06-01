import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
class note(
    @PrimaryKey(autoGenerate = true)
    private var id: Int,
    private val title: String,
    private val description: String,
    private val dayOfWeek: Int,
    private val priority: Int
) {
    fun getId():Int{
        return id
    }

    fun getTitle(): String {
        return title
    }

    fun getDescriotion(): String {
        return description
    }

    fun getDayOfWeek(): Int {
        return dayOfWeek
    }

    fun getPriority(): Int {
        return priority
    }

    fun getDayAsString(posotion:Int): String {
        return when (posotion) {
            1 -> "Понедельник"
            2 -> "Вторник"
            3 -> "Среда"
            4 -> "Четверг"
            5 -> "Пятница"
            6 -> "Суббота"
            7 -> "Воскресенье"
            else -> ""
        }

    }

}

