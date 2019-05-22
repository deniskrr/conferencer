package ro.deepster.conferencemanagementsystem.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

enum class Verdict {
    UNANSWERED, REJECTED, ACCEPTED, DISCUSS
}

@Parcelize
data class Proposal(
    val title: String = "",
    val abstract: String = "",
    val topics: String = "",
    val keywords: String = "",
    val author: String = "",
    val paperLink: String = "",
    val messages: MutableList<Message> = mutableListOf(),
    val bidders: MutableMap<String, Verdict> = mutableMapOf()
) : Parcelable {
    fun addBidder(bidder: String) {
        bidders[bidder] = Verdict.UNANSWERED
    }

    fun rejectProposal(bidder: String) {
        bidders[bidder] = Verdict.REJECTED
    }

    fun acceptProposal(bidder: String) {
        bidders[bidder] = Verdict.ACCEPTED
    }

    fun sendMessage(sender: String, text: String) {
        messages.add(Message(sender, text))
    }
}