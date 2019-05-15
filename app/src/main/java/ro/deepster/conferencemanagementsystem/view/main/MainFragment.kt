package ro.deepster.conferencemanagementsystem.view.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_fragment.*
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.model.ConferenceItem
import ro.deepster.conferencemanagementsystem.view.conference.RoleActivity
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

        val adapter = GroupAdapter<ViewHolder>()

        // Set adapter item onClickListener to navigate to Conference activity
        adapter.setOnItemClickListener { item, view ->
            if (item is ConferenceItem) {
                val intent = Intent(context, RoleActivity::class.java)
                intent.putExtra(RoleActivity.CONFERENCE_NAME, item.conference)
                startActivity(intent)
            }
        }
        recycler_conferences_main.adapter = adapter
        recycler_conferences_main.layoutManager = LinearLayoutManager(activity)

        viewModel.conferences.observe(this, Observer {
            adapter.clear()

            adapter.addAll(
                viewModel.getConferences().map { title ->
                    ConferenceItem(title)
                }
            )
        })
    }

}
