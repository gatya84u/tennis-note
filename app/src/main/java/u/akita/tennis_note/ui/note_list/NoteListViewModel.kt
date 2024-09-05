package u.akita.tennis_note.ui.note_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import u.akita.tennis_note.TennisNote
import u.akita.tennis_note.repository.dao.MatchInfoDao
import u.akita.tennis_note.repository.dataclass.MatchWithDate

class NoteListViewModel(application: Application) : AndroidViewModel(application) {
    private val matchInfoDao: MatchInfoDao

    init {
        val database = TennisNote.MatchDatabase.getDatabase(application)
        matchInfoDao = database.matchInfoDao()
    }
    fun getListData(): LiveData<List<MatchWithDate>> {
        return matchInfoDao.getMatchInfo()
    }
}