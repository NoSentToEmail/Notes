import android.os.Parcel
import android.os.Parcelable
import androidx.cardview.widget.CardView


class note(
    private val title: String,
    private val description: String,
    private val dayOfWeek: String,
    private val priority: Int
)  {

        fun getTitle(): String {
            return title
        }

        fun getDescriotion(): String {
            return description
        }

        fun getDayOfWeek(): String {
            return dayOfWeek
        }

        fun getPriority(): Int {
            return priority
        }

    }

