package u.akita.tennis_note.repository.dataclass

import androidx.room.ColumnInfo

data class MatchWithDate (
    @ColumnInfo(name = "match_id") val matchId: String,
    @ColumnInfo(name = "match_date_id") val matchDateId: String,
    @ColumnInfo(name = "match_date") val matchDate: String,
    @ColumnInfo(name = "match_type") val matchType: String,
    @ColumnInfo(name = "opponent") val opponent: String?,
    @ColumnInfo(name = "match_score") val matchScore: String?
)