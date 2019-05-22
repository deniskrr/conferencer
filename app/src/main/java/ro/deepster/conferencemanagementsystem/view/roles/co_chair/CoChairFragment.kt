package ro.deepster.conferencemanagementsystem.view.roles.co_chair

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.co_chair_fragment.*
import ro.deepster.conferencemanagementsystem.R

class CoChairFragment : Fragment() {

    private lateinit var viewModel: CoChairViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.co_chair_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CoChairViewModel::class.java)

        button_undecided_evaluations.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_cochair_to_undecided_paper))
    }
}
