package u.akita.tennis_note.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import u.akita.tennis_note.repository.model.Checklist

    @Dao
    interface ChecklistDao {
        @Query("SELECT * FROM checklist")
        fun getAll(): LiveData<List<Checklist>>
    }