package u.akita.tennis_note.repository.dao

import androidx.room.Dao
import androidx.room.Query
import u.akita.tennis_note.repository.model.MatchDateManager

@Dao
interface MatchDateManagerDao {
    @Query("SELECT * FROM match_date_manager")
    fun getAll(): List<MatchDateManager>
}