package u.akita.tennis_note.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import u.akita.tennis_note.repository.model.MatchManager

@Dao
interface MatchManagerDao {
    @Query("SELECT * FROM match_manager")
    fun getAll(): List<MatchManager>

    @Insert
    fun insert(matchManage: MatchManager): Long

    @Update
    fun update(matchManage: MatchManager)
}