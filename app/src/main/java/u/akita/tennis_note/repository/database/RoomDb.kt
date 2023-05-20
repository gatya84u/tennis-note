package u.akita.tennis_note.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import u.akita.tennis_note.repository.dao.CheckListDao
import u.akita.tennis_note.repository.dao.MatchManagerDao
import u.akita.tennis_note.repository.model.CheckList
import u.akita.tennis_note.repository.model.MatchDateManager
import u.akita.tennis_note.repository.model.MatchManager

@Database(entities = [CheckList::class, MatchDateManager::class, MatchManager::class], version = 1)
abstract class AppDatabase:RoomDatabase(){
  abstract fun checkListDao(): CheckListDao
  abstract fun matchDateManagerDao(): MatchManagerDao
  abstract fun matchManagerDao(): MatchManagerDao
}