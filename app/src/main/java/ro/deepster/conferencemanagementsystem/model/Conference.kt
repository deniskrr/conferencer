package ro.deepster.conferencemanagementsystem.model

enum class Period {
    CALL_FOR_PAPERS,

}

class Conference(
    val title: String = "",
    val co_chair: String = "",
    val reviewers: List<String> = mutableListOf(),
    val period: Period = Period.CALL_FOR_PAPERS
)