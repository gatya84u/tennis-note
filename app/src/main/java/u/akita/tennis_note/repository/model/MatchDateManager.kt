package u.akita.tennis_note.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "match_date_manager")
data class MatchDateManager(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val matchDate: String,
    val matchType: String,
    val matchName: String,
    val matchPlace: String
)