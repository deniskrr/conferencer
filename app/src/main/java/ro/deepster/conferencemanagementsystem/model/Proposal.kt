package ro.deepster.conferencemanagementsystem.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Proposal(
    val title: String = "",
    val abstract: String = "",
    val topics: String = "",
    val keywords: String = "",
    val author: String = "",
    val paperLink: String = "",
    val bidders: MutableList<String> = mutableListOf()
) : Parcelable {
    fun addBidder(bidder: String) {
        bidders.add(bidder)
    }
}