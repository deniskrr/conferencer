package ro.deepster.conferencemanagementsystem.view.roles.reviewer

import com.google.firebase.firestore.FirebaseFirestore
import ro.deepster.conferencemanagementsystem.model.Proposal
import ro.deepster.conferencemanagementsystem.view.roles.RoleActivity
import ro.deepster.conferencemanagementsystem.view.roles.RoleViewModel

class ReviewerViewModel : RoleViewModel() {
    fun addBidder(proposal: Proposal) {
        val user = currentUser.value!!.username
        val conference = currentConference.value!!
        conference.proposals.remove(proposal)
        proposal.addBidder(user)
        conference.proposals.add(proposal)
        FirebaseFirestore.getInstance().collection("conferences").document(RoleActivity.conferenceName).set(conference)
    }

    fun acceptPaper(proposal: Proposal) {
        val user = currentUser.value!!.username
        val conference = currentConference.value!!
        conference.proposals.remove(proposal)
        proposal.acceptProposal(user)
        conference.proposals.add(proposal)
        FirebaseFirestore.getInstance().collection("conferences").document(RoleActivity.conferenceName).set(conference)
    }

    fun rejectPaper(proposal: Proposal) {
        val user = currentUser.value!!.username
        val conference = currentConference.value!!
        conference.proposals.remove(proposal)
        proposal.rejectProposal(user)
        conference.proposals.add(proposal)
        FirebaseFirestore.getInstance().collection("conferences").document(RoleActivity.conferenceName).set(conference)
    }

}
