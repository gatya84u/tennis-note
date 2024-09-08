package u.akita.tennis_note.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import u.akita.tennis_note.repository.model.MatchManager

@Dao
interface MatchManagerDao {
    @Query("SELECT * FROM match_manager")
    fun getAll(): LiveData<List<MatchManager>>

    @Upsert
    fun upsert(matchManage: MatchManager): Long
}