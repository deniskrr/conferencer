package ro.deepster.conferencemanagementsystem.view.roles.reviewer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_role_activity.*
import kotlinx.android.synthetic.main.fragment_evaluate_paper.*
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.model.Proposal
import ro.deepster.conferencemanagementsystem.model.ProposalItem


class EvaluatePaperFragment : Fragment() {

    companion object {
        const val PROPOSAL = "proposal"
    }

    val adapter = GroupAdapter<ViewHolder>()
    private lateinit var viewModel: ReviewerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_evaluate_paper, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(ReviewerViewModel::class.java)

        adapter.setOnItemClickListener { item, view ->
            if (item is ProposalItem) {
                val controller = NavHostFragment.findNavController(role_content)
                controller.navigate(R.id.action_evaluate_extended, bundleOf(PROPOSAL to item.proposal))
            }
        }

        recycler_bidden_proposals.adapter = adapter
        recycler_bidden_proposals.layoutManager = LinearLayoutManager(activity)

        viewModel.currentConference.observe(this, Observer { currentConference ->
            adapter.clear()

            val currentUser = viewModel.currentUser.value!!.username

            adapter.addAll(
                currentConference.proposals.filter { proposal: Proposal ->
                    // Show proposals that you bid on
                    currentUser in proposal.bidders
                }.map { proposal -> ProposalItem(proposal) }
            )
        })
    }

}


