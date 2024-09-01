package u.akita.tennis_note.enum

enum class MatchType(val value: String, val displayValue: String) {
    ITEM1("1", "シングルス"),
    ITEM2("2", "ダブルス"),
    ITEM3("3","MIXダブルス"),
    ITEM4("4","団体戦(シングルス)"),
    ITEM5("5", "団体戦(ダブルス"),
    ITEM6("6","団体戦(MIX)")
}

enum class SearchMatchType(val value: String, val displayValue: String){
    ITEM0("0", "全て"),
    ITEM1("1", "シングルス"),
    ITEM2("2", "ダブルス"),
    ITEM3("3","MIXダブルス"),
    ITEM4("4","団体戦(シングルス)"),
    ITEM5("5", "団体戦(ダブルス"),
    ITEM6("6","団体戦(MIX)")
}