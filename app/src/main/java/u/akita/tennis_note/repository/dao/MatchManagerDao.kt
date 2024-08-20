package u.akita.tennis_note.repository.dao

import androidx.room.Dao
import androidx.room.Query
import u.akita.tennis_note.repository.model.MatchManager

@Dao
interface MatchManagerDao {
    @Query("SELECT * FROM match_manager")
    fun getAll(): List<MatchManager>
}