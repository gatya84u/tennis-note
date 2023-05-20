package u.akita.tennis_note.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text

@Entity
data class MatchManager (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "match_date_id") val matchDateId: Int?,
    @ColumnInfo(name = "match_type") val matchType: Int?, //TODO enum化 0:シングルス 1:ダブルス 2:MIX
    @ColumnInfo(name = "get_game_count") val getGameCount: Int?,
    @ColumnInfo(name = "lost_game_count") val lostGameCount: Int?,
    @ColumnInfo(name = "look_back") val lookBack: Text
)