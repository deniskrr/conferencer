package ro.deepster.conferencemanagementsystem.view.roles.co_chair

import com.google.firebase.firestore.FirebaseFirestore
import ro.deepster.conferencemanagementsystem.model.Proposal
import ro.deepster.conferencemanagementsystem.model.Verdict
import ro.deepster.conferencemanagementsystem.view.roles.RoleActivity
import ro.deepster.conferencemanagementsystem.view.roles.RoleViewModel

class CoChairViewModel : RoleViewModel() {

    fun changeVerdict(proposal: Proposal, verdict: Verdict) {
        val conference = currentConference.value!!
        conference.proposals.remove(proposal)
        for (key in proposal.bidders.keys) {
            proposal.bidders[key] = verdict
        }
        conference.proposals.add(proposal)
        FirebaseFirestore.getInstance().collection("conferences").document(RoleActivity.conferenceName).set(conference)
    }

}
