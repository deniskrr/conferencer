package ro.deepster.conferencemanagementsystem.view.roles.reviewer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.model.Proposal


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
    }


}
