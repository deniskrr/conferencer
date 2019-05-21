package ro.deepster.conferencemanagementsystem.view.roles.reviewer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import kotlinx.android.synthetic.main.activity_role_activity.*
import kotlinx.android.synthetic.main.fragment_evaluate_paper_extended.*
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.model.Proposal


class EvaluatePaperExtendedFragment : Fragment() {

    private lateinit var proposal: Proposal
    private lateinit var viewModel: ReviewerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_evaluate_paper_extended, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(ReviewerViewModel::class.java)
        proposal = arguments?.get(EvaluatePaperFragment.PROPOSAL) as Proposal

        text_paper.text = proposal.title
        text_abstract.text = proposal.abstract
        button_see_paper.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(proposal.paperLink))
            startActivity(browserIntent)
        }

        button_accept.setOnClickListener {
            viewModel.acceptPaper(proposal)
            findNavController(role_content).navigateUp()
        }

        button_reject.setOnClickListener {
            viewModel.rejectPaper(proposal)
            findNavController(role_content).navigateUp()
        }
    }

}
