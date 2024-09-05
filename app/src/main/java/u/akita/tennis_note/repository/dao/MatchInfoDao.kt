package u.akita.tennis_note.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import u.akita.tennis_note.repository.dataclass.MatchWithDate

@Dao
interface MatchInfoDao {
    @Query(
        """SELECT mm.id AS match_id, mm.match_date_id, mm.opponent, 
            (IFNULL(mm.gain_game_count, '0') || '-' || IFNULL(mm.lost_game_count, '0')) AS match_score, 
            md.match_date, md.match_type
            FROM match_manager mm
            JOIN match_date_manager md ON mm.match_date_id = md.id
    """
    )
    fun getMatchInfo(): LiveData<List<MatchWithDate>>
}