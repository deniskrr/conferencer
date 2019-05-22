package ro.deepster.conferencemanagementsystem.model

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.your_message_item.view.*
import ro.deepster.conferencemanagementsystem.R

class MyMessageItem(val message: Message) : Item<ViewHolder>() {
    override fun getLayout(): Int = R.layout.my_message_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.text_sender_message.text = message.sender
        viewHolder.itemView.text_message.text = message.text
    }
}