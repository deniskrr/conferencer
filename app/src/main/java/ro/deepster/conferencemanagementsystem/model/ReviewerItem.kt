package ro.deepster.conferencemanagementsystem.model

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.reviewer.view.*
import ro.deepster.conferencemanagementsystem.R

class ReviewerItem(val username: String) : Item<ViewHolder>() {
    override fun getLayout(): Int = R.layout.reviewer

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.text_reviewer.text = username
    }
}