package u.akita.tennis_note.ui.checklist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import u.akita.tennis_note.TennisNote
import u.akita.tennis_note.databinding.DialogCheckItemBinding
import u.akita.tennis_note.enum.CheckCategory
import u.akita.tennis_note.repository.model.Checklist

class ChecklistViewModel(application: Application): AndroidViewModel(application) {
    private val checklistDao = getApplication<TennisNote>().database.checklistDao()
    fun getListData(): LiveData<List<Checklist>> {
        return checklistDao.getAll()
    }

    suspend fun registerNewCheckItem(binding: DialogCheckItemBinding): Long {
        val selectedCategoryPos = binding.checkCategory.selectedItemPosition
        val selectedCategory = CheckCategory.values()[selectedCategoryPos].value
        val themeTitle = binding.checkContents.text.toString()

        val checklist = Checklist(0, selectedCategory.toInt(), themeTitle, false, "")
        return withContext(Dispatchers.IO) {
            val id = checklistDao.insert(checklist)
            Log.d("id", id.toString())
            id
        }
    }

    suspend fun updateCheckItem(checklistItem: Checklist){
        withContext(Dispatchers.IO){
            checklistDao.update(checklistItem)
        }
    }
}