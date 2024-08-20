package u.akita.tennis_note.repository.dao

import androidx.room.Dao
import androidx.room.Query
import u.akita.tennis_note.repository.model.CheckList

@Dao
interface CheckListDao {
    @Query("SELECT * FROM checklist")
    fun getAll(): List<CheckList>
}