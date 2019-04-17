package ro.deepster.conferencemanagementsystem.model

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.conference.view.*
import ro.deepster.conferencemanagementsystem.R

class ConferenceItem(val conference: String) : Item<ViewHolder>() {
    override fun getLayout(): Int = R.layout.conference

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.text_conference.text = conference
    }
}