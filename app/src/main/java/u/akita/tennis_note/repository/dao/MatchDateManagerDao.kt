package u.akita.tennis_note.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import u.akita.tennis_note.repository.model.MatchDateManager

@Dao
interface MatchDateManagerDao {
    @Query("SELECT * FROM match_date_manager")
    fun getAll(): List<MatchDateManager>

    @Upsert
    fun upsert(matchDateManager: MatchDateManager): Long

}