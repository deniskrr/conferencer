package ro.deepster.conferencemanagementsystem.view.roles.reviewer


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
import kotlinx.android.synthetic.main.fragment_discuss_paper_extended.*
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.model.MyMessageItem
import ro.deepster.conferencemanagementsystem.model.Proposal
import ro.deepster.conferencemanagementsystem.model.YourMessageItem


class DiscussPaperExtendedFragment : Fragment() {

    private lateinit var viewModel: ReviewerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discuss_paper_extended, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(ReviewerViewModel::class.java)

        val proposal = arguments!!.get(DiscussPaperFragment.PROPOSAL) as Proposal

        val adapter = GroupAdapter<ViewHolder>()

        recycler_discuss_messages.adapter = adapter
        recycler_discuss_messages.layoutManager = LinearLayoutManager(context)

        viewModel.currentConference.observe(this, Observer { currentConference ->
            adapter.clear()

            val currentUser = viewModel.currentUser.value!!.username

            adapter.addAll(
                currentConference.proposals.find { proposalX: Proposal ->
                    proposalX.title == proposal.title
                }!!.messages.map { message ->
                    if (currentUser == message.sender) {
                        MyMessageItem(message)
                    } else {
                        YourMessageItem(message)
                    }
                }
            )

            recycler_discuss_messages.scrollToPosition(adapter.itemCount - 1)
        })

        button_send_message.setOnClickListener {
            val text = textlayout_message_text.editText!!.text.toString()

            viewModel.sendMessage(proposal, text)
        }
    }


}
