package ro.deepster.conferencemanagementsystem.view.roles.co_chair


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import kotlinx.android.synthetic.main.activity_role_activity.*
import kotlinx.android.synthetic.main.fragment_undecided_papers_extended.*
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.model.Proposal
import ro.deepster.conferencemanagementsystem.model.Verdict

class UndecidedPapersExtendedFragment : Fragment() {

    private lateinit var viewModel: CoChairViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_undecided_papers_extended, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(CoChairViewModel::class.java)

        val proposal = arguments!!.get(UndecidedPapersFragment.PROPOSAL) as Proposal

        button_reject_chair.setOnClickListener {
            viewModel.changeVerdict(proposal, Verdict.REJECTED)
        }

        button_accept_chair.setOnClickListener {
            viewModel.changeVerdict(proposal, Verdict.ACCEPTED)
            findNavController(role_content).navigateUp()
        }

        button_discuss_chair.setOnClickListener {
            viewModel.changeVerdict(proposal, Verdict.DISCUSS)
        }
    }


}
