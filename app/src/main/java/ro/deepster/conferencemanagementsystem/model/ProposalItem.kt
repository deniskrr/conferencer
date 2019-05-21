package ro.deepster.conferencemanagementsystem.model

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.proposal.view.*
import ro.deepster.conferencemanagementsystem.R

class ProposalItem(val proposal: Proposal) : Item<ViewHolder>() {
    override fun getLayout(): Int = R.layout.proposal

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.text_proposal.text = proposal.title
    }
}