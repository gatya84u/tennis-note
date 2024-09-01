package u.akita.tennis_note.ui.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import u.akita.tennis_note.TennisNote
import u.akita.tennis_note.databinding.FragmentNoteBinding
import u.akita.tennis_note.enum.MatchType
import u.akita.tennis_note.repository.model.MatchDateManager
import u.akita.tennis_note.repository.model.MatchManager
import java.sql.Types.NULL
import u.akita.tennis_note.repository.dao.MatchDateManagerDao

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private val dateDao = getApplication<TennisNote>().database.matchDateManagerDao()
    private val matchDao = getApplication<TennisNote>().database.matchManagerDao()

    fun registerMatchData(binding: FragmentNoteBinding) {

        val matchDate = binding.matchDate.text.toString()
        val matchName = binding.matchName.text.toString()
        val opponent = binding.opponent.text.toString()?:null
        val selfScore = binding.scoreSelf.text.toString().toIntOrNull()?:0
        val opponentScore = binding.scoreOpponent.text.toString().toIntOrNull()?:0
        val lookBack = binding.lookingBackGame.text.toString()?:null
        val selectedItem = binding.matchType.selectedItem as String
        val selectedMatchType = MatchType.values().firstOrNull { it.displayValue == selectedItem }?.value
        var matchDateId = binding.matchDateId.id
        var matchId = binding.matchId.id

        if(matchDateId == NULL){
            val matchDateManager = MatchDateManager(0, matchDate, selectedMatchType.toString(), matchName, null)
            matchDateId = dateDao.insert(matchDateManager).toInt()

            val matchManager = MatchManager(0, matchDateId, opponent, selfScore, opponentScore, lookBack, null)
            matchDao.insert(matchManager)
        }else{
//            val matchDateManager = MatchDateManager(matchDateId, matchDate, selectedMatchType.toString(), matchName, null)
//            dateDao.update(matchDateManager)

            val matchManager = MatchManager(matchId, matchDateId, opponent, selfScore, opponentScore, lookBack, null)
            matchDao.update(matchManager)
        }
    }
}