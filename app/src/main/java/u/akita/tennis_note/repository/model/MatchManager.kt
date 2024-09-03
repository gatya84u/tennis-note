package u.akita.tennis_note.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text

@Entity (tableName = "match_manager")
data class MatchManager (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name="match_date_id") val matchDateId: Int,
    @ColumnInfo(name = "opponent") var opponent: String?,
    @ColumnInfo(name = "gain_game_count") var gainGameCount: Int?,
    @ColumnInfo(name = "lost_game_count") var lostGameCount: Int?,
    @ColumnInfo(name = "match_note") var matchNote: String?,
    @ColumnInfo(name = "movie_url") var movieUrl: String?
)