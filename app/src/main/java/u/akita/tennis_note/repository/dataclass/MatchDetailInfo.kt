package u.akita.tennis_note.repository.dataclass

import androidx.room.ColumnInfo

data class MatchDetailInfo(
    @ColumnInfo(name = "match_id") val matchId: String,
    @ColumnInfo(name = "match_date_id") val matchDateId: String,
    @ColumnInfo(name = "match_date") val matchDate: String,
    @ColumnInfo(name = "match_type") val matchType: String,
    @ColumnInfo(name = "opponent") val opponent: String?,
    @ColumnInfo(name = "gain_game_count") val gainGameCount: Int?,
    @ColumnInfo(name = "lost_game_count") val lostGameCount: Int?,
    @ColumnInfo(name = "match_note") val matchNote: String?,
    @ColumnInfo(name = "movie_url") val movieUrl: String?,
    @ColumnInfo(name = "match_name") val matchName: String?
)
