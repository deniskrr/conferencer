package ro.deepster.conferencemanagementsystem.view.roles.co_chair


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ro.deepster.conferencemanagementsystem.R

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
    }


}
