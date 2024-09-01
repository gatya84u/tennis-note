package u.akita.tennis_note.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import u.akita.tennis_note.repository.dao.ChecklistDao
import u.akita.tennis_note.repository.dao.MatchManagerDao
import u.akita.tennis_note.repository.dao.MatchDateManagerDao
import u.akita.tennis_note.repository.model.Checklist
import u.akita.tennis_note.repository.model.MatchDateManager
import u.akita.tennis_note.repository.model.MatchManager

@Database(entities = [Checklist::class, MatchDateManager::class, MatchManager::class], version = 2, exportSchema = false)
abstract class AppDatabase:RoomDatabase(){
  abstract fun checklistDao(): ChecklistDao
  abstract fun matchDateManagerDao(): MatchDateManagerDao
  abstract fun matchManagerDao(): MatchManagerDao
}