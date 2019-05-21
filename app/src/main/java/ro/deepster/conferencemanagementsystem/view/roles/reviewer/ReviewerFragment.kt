package ro.deepster.conferencemanagementsystem.view.roles.reviewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.reviewer_fragment.*
import ro.deepster.conferencemanagementsystem.R

class ReviewerFragment : Fragment() {


    private lateinit var viewModel: ReviewerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reviewer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(ReviewerViewModel::class.java)

        button_bid_proposal.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_reviewer_to_bid_proposal))
    }

}
