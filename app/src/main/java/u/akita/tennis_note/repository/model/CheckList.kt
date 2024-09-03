package u.akita.tennis_note.repository.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "checklist")
data class Checklist(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var themeCategory: Int,
    var themeTitle: String,
    var completeFlag: Boolean,
    var completeDate: String
)
