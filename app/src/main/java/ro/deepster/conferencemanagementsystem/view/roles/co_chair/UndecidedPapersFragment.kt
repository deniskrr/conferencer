package ro.deepster.conferencemanagementsystem.view.roles.co_chair

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_undecided_papers.*
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.model.Proposal
import ro.deepster.conferencemanagementsystem.model.ProposalItem
import ro.deepster.conferencemanagementsystem.model.Verdict


class UndecidedPapersFragment : Fragment() {

    private lateinit var viewModel: CoChairViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_undecided_papers, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!).get(CoChairViewModel::class.java)

        val adapter = GroupAdapter<ViewHolder>()
        recycler_undecided_papers.adapter = adapter
        recycler_undecided_papers.layoutManager = LinearLayoutManager(activity)

        adapter.setOnItemClickListener { item, view ->
            if (item is ProposalItem) {
//                findNavController(role_content).navigate()
            }
        }

        viewModel.currentConference.observe(this, Observer { currentConference ->
            adapter.clear()

            adapter.addAll(currentConference.proposals.filter { proposal: Proposal ->
                // Show proposals that have been both accepted and rejected
                Verdict.ACCEPTED in proposal.bidders.values && Verdict.REJECTED in proposal.bidders.values
            }.map { proposal -> ProposalItem(proposal) })
        })

    }


}
