package u.akita.tennis_note

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import u.akita.tennis_note.repository.dao.ChecklistDao
import u.akita.tennis_note.repository.dao.MatchDateManagerDao
import u.akita.tennis_note.repository.dao.MatchInfoDao
import u.akita.tennis_note.repository.dao.MatchManagerDao
import u.akita.tennis_note.repository.model.Checklist
import u.akita.tennis_note.repository.model.MatchDateManager
import u.akita.tennis_note.repository.model.MatchManager

class TennisNote : Application() {
    lateinit var database: MatchDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        // データベースを初期化
        database = Room.databaseBuilder(
            applicationContext,
            MatchDatabase::class.java, "match-note"
        ).build()
    }

    @Database(entities = [MatchManager::class, MatchDateManager::class, Checklist::class], version = 2, exportSchema = false)
    abstract class MatchDatabase : RoomDatabase() {
        abstract fun matchInfoDao(): MatchInfoDao
        abstract fun matchDateManagerDao(): MatchDateManagerDao
        abstract fun checklistDao(): ChecklistDao
        abstract fun matchManagerDao(): MatchManagerDao

        companion object {
            @Volatile
            private var INSTANCE: MatchDatabase? = null

            fun getDatabase(context: Context): MatchDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MatchDatabase::class.java,
                        "match-note"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }
}
