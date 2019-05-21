package ro.deepster.conferencemanagementsystem.view.roles.reviewer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_bid_proposal.*
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.model.Proposal
import ro.deepster.conferencemanagementsystem.model.ProposalItem

class BidProposalFragment : Fragment() {

    val adapter = GroupAdapter<ViewHolder>()
    private lateinit var viewModel: ReviewerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bid_proposal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(ReviewerViewModel::class.java)

        val myCallBack = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.getItem(viewHolder.adapterPosition)
                if (item is ProposalItem) {
                    viewModel.addBidder(item.proposal)
                    adapter.removeGroup(viewHolder.adapterPosition)
                    adapter.notifyItemChanged(viewHolder.adapterPosition)
                    Snackbar.make(viewHolder.itemView, "You bidded on ${item.proposal.title}", Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }

        val myHelper = ItemTouchHelper(myCallBack)

        adapter.setOnItemClickListener { item, view ->
            if (item is ProposalItem) {
                Snackbar.make(view, item.proposal.abstract, Snackbar.LENGTH_LONG).show()
            }
        }
        recycler_proposals.adapter = adapter
        recycler_proposals.layoutManager = LinearLayoutManager(activity)

        myHelper.attachToRecyclerView(recycler_proposals)
        viewModel.currentConference.observe(this, Observer { currentConference ->
            adapter.clear()

            val currentUser = viewModel.currentUser.value!!.username

            adapter.addAll(
                currentConference.proposals.filter { proposal: Proposal ->
                    // Show proposals that you didn't bid on
                    currentUser !in proposal.bidders
                }.map { proposal -> ProposalItem(proposal) }
            )
        })


    }

}