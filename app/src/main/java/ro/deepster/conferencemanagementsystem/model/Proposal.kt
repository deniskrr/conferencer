package ro.deepster.conferencemanagementsystem.model

data class Proposal(
    val title: String,
    val topics : String,
    val keywords : String,
    val authors : List<String>,
    val paperLink : String


)