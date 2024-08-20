package u.akita.tennis_note

import android.app.Application
import androidx.room.Room
import u.akita.tennis_note.repository.database.AppDatabase

class TennisNote: Application() {
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        // データベースを初期化
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "match-note"
        ).build()
    }
}