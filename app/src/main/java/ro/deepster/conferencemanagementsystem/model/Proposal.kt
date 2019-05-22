package ro.deepster.conferencemanagementsystem.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

enum class Verdict {
    UNANSWERED, REJECTED, ACCEPTED
}

@Parcelize
data class Proposal(
    val title: String = "",
    val abstract: String = "",
    val topics: String = "",
    val keywords: String = "",
    val author: String = "",
    val paperLink: String = "",
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
}