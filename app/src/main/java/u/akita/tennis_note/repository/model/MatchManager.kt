package u.akita.tennis_note.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text

@Entity (tableName = "match_manager")
data class MatchManager (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val matchDateId: Int,
    val matchNumber: Int,
    val opponent: String,
    val gainGameCount: Int,
    val lossGameCount: Int,
    val matchNote: String,
    val movieUrl: String
)