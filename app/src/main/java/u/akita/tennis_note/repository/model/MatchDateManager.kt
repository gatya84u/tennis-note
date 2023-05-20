package u.akita.tennis_note.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class MatchDateManager(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "match_date") val matchDate: Date?,
    @ColumnInfo(name = "match_name") val matchName: String?
)