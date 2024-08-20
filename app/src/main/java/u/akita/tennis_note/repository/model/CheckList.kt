package u.akita.tennis_note.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text
import java.util.Date

@Entity(tableName = "checklist")
data class CheckList(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val themeCategory: Int, //TODO enum化 0:戦略・戦術 1:サーブ 2:ボレー 3:ストローク・・・
    val themeTitle: String,
    val completeFlag: Boolean,
    val completeDate: String
)
