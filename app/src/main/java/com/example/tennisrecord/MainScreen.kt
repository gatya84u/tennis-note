package com.example.tennisrecord

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun MainScreen() {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("試合結果リスト", "カレンダー", "課題リスト")

    Column {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> Text("試合結果リスト Screen")
            1 -> Text("カレンダー Screen")
            2 -> Text("課題リスト Screen")
        }
    }
}
