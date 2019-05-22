package ro.deepster.conferencemanagementsystem.model

enum class Period {
    CALL_FOR_PAPERS,

}

class Conference(
    val title: String = "",
    val co_chair: String = "",
    val reviewers: List<String> = listOf(),
    val proposals: MutableList<Proposal> = mutableListOf(),
    val period: Period = Period.CALL_FOR_PAPERS
) {
    fun addProposal(proposal: Proposal) {
        proposals.add(proposal)
    }
}