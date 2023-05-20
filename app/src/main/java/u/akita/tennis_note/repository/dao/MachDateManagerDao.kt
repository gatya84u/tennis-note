package u.akita.tennis_note.repository.dao

import androidx.room.Dao
import androidx.room.Query
import u.akita.tennis_note.repository.model.MatchDateManager

@Dao
interface MachDateManagerDao {
    @Query("SELECT * FROM matchdatemanager")
    fun getAll(): List<MatchDateManager>
}