package ro.deepster.conferencemanagementsystem.view.roles.reviewer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_role_activity.*
import kotlinx.android.synthetic.main.fragment_discuss_paper.*
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.model.Proposal
import ro.deepster.conferencemanagementsystem.model.ProposalItem
import ro.deepster.conferencemanagementsystem.model.Verdict


class DiscussPaperFragment : Fragment() {
    companion object {
        const val PROPOSAL = "proposal"
    }

    private lateinit var viewModel: ReviewerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discuss_paper, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(ReviewerViewModel::class.java)

        val adapter = GroupAdapter<ViewHolder>()

        adapter.setOnItemClickListener { item, view ->
            if (item is ProposalItem) {
                findNavController(role_content).navigate(
                    R.id.action_discuss_to_extended,
                    bundleOf(PROPOSAL to item.proposal)
                )
            }
        }

        recycler_discuss_papers.adapter = adapter
        recycler_discuss_papers.layoutManager = LinearLayoutManager(context)

        viewModel.currentConference.observe(this, Observer {
            adapter.clear()

            val currentUser = viewModel.currentUser.value!!.username
            val currentConference = viewModel.currentConference.value!!

            adapter.addAll(
                currentConference.proposals.filter { proposal: Proposal ->
                    proposal.bidders[currentUser] == Verdict.DISCUSS
                }.map { proposal -> ProposalItem(proposal) }
            )
        })
    }

}
