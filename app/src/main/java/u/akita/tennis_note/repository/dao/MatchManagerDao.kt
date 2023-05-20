package u.akita.tennis_note.repository.dao

import androidx.room.Dao
import androidx.room.Query
import u.akita.tennis_note.repository.model.MatchManager

@Dao
interface MatchManagerDao {
    @Query("SELECT * FROM MatchManager")
    fun getAll(): List<MatchManager>
}