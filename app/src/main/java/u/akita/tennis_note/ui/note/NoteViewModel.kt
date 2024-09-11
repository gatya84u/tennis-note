package u.akita.tennis_note.ui.note

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import u.akita.tennis_note.TennisNote
import u.akita.tennis_note.databinding.FragmentNoteBinding
import u.akita.tennis_note.enum.MatchType
import u.akita.tennis_note.repository.model.MatchDateManager
import u.akita.tennis_note.repository.model.MatchManager
import java.sql.Types.NULL
import u.akita.tennis_note.repository.dao.MatchDateManagerDao
import u.akita.tennis_note.repository.dataclass.MatchDetailInfo

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private val dateDao = getApplication<TennisNote>().database.matchDateManagerDao()
    private val matchDao = getApplication<TennisNote>().database.matchManagerDao()
    private val matchInfoDao = getApplication<TennisNote>().database.matchInfoDao()

    suspend fun getMatchDetailData(matchId: String): MatchDetailInfo {
        return withContext(Dispatchers.IO) {
            matchInfoDao.getMatchDetailInfo(matchId.toInt())
        }
    }
    suspend fun registerMatchData(binding: FragmentNoteBinding) {

        val matchDate = binding.matchDate.text.toString()
        val matchName = binding.matchName.text.toString()
        val opponent = binding.opponent.text.toString()?:null
        val selfScore = binding.scoreSelf.text.toString().toIntOrNull()?:0
        val opponentScore = binding.scoreOpponent.text.toString().toIntOrNull()?:0
        val lookBack = binding.lookingBackGame.text.toString()?:null
        val selectedItem = binding.matchType.selectedItem as String
        val selectedMatchType = MatchType.values().firstOrNull { it.displayValue == selectedItem }?.value
        var matchDateId = binding.matchDateId.text.toString().toInt()
        var matchId = binding.matchId.text.toString().toInt()

        return withContext(Dispatchers.IO){
            val matchDateManager = MatchDateManager(matchDateId, matchDate, selectedMatchType.toString(), matchName, null)
            val registerDateId = dateDao.upsert(matchDateManager).toInt()

            Log.i("registerMatchData", registerDateId.toString())

            val matchManager = MatchManager(matchId, registerDateId, opponent, selfScore, opponentScore, lookBack, null)
            val registerMatchId = matchDao.upsert(matchManager).toInt()

            Log.i("registerMatchData", registerMatchId.toString())

            var resultMap: Map<String, Int> = mapOf(
                "matchDateId" to registerDateId,
                "matchId" to registerMatchId
            )
            resultMap
        }
    }
}