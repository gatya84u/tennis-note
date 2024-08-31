package u.akita.tennis_note.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "match_date_manager")
data class MatchDateManager(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "match_date")val matchDate: String,
    @ColumnInfo(name = "match_type")val matchType: String,
    @ColumnInfo(name = "match_name")val matchName: String,
    @ColumnInfo(name = "match_place")val matchPlace: String?
)