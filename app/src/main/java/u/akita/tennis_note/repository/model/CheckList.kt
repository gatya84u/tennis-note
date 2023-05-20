package u.akita.tennis_note.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text

@Entity
data class CheckList(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "category") val category: Int, //TODO enum化 0:戦略・戦術 1:サーブ 2:ボレー 3:ストローク・・・
    @ColumnInfo(name = "check_point") val checkPoint: Text,
    @ColumnInfo(name = "complete_flag") val completeFlag: Boolean,
    @ColumnInfo(name = "match_id") val matchId: Int
)
