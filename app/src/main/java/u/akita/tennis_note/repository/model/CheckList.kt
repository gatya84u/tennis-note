package u.akita.tennis_note.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text
import java.util.Date

@Entity(tableName = "checklist")
data class Checklist(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val themeCategory: Int,
    val themeTitle: String,
    val completeFlag: Boolean,
    val completeDate: String
)
