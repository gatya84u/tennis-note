package u.akita.tennis_note.ui.checklist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import u.akita.tennis_note.TennisNote
import u.akita.tennis_note.databinding.FragmentChecklistBinding
import u.akita.tennis_note.repository.model.Checklist

class ChecklistViewModel(application: Application): AndroidViewModel(application) {
    private val checklistDao = getApplication<TennisNote>().database.checklistDao()
    fun getListData(): LiveData<List<Checklist>> {
        return checklistDao.getAll()
//        var allChecklist = checklistDao.getAll()
//        Log.i("allChecklist", allChecklist.toString())
    }
}