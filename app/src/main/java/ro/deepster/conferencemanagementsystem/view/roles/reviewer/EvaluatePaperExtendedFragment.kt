package ro.deepster.conferencemanagementsystem.view.roles.reviewer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_evaluate_paper_extended.*
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.model.Proposal


class EvaluatePaperExtendedFragment : Fragment() {

    private lateinit var proposal: Proposal

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_evaluate_paper_extended, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        proposal = arguments?.get(EvaluatePaperFragment.PROPOSAL) as Proposal

        text_paper.text = proposal.title
        text_abstract.text = proposal.abstract
        button_see_paper.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(proposal.paperLink))
            startActivity(browserIntent)
        }
    }

}
