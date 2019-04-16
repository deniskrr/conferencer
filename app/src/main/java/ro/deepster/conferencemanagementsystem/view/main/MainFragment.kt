package ro.deepster.conferencemanagementsystem.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_fragment.*
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.viewmodel.MainViewModel

class MainFragment : Fragment() {


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val controller = findNavController(nav_host_fragment)

        fab_new_conference.setOnClickListener {
            controller.navigate(R.id.action_main_to_new)
        }
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

}
