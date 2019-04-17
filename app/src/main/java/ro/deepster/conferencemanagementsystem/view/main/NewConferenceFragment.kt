package ro.deepster.conferencemanagementsystem.view.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.new_conference_fragment.*
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.model.Conference
import ro.deepster.conferencemanagementsystem.model.ReviewerItem
import ro.deepster.conferencemanagementsystem.viewmodel.MainViewModel

class NewConferenceFragment : Fragment() {

    companion object {
        fun newInstance() = NewConferenceFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_conference_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.users.observe(this, Observer {
            spinner_co_chair_new.setItems(viewModel.getUsers())
            spinner_reviewers_new.setItems(viewModel.getUsers())
        })

        val adapter = GroupAdapter<ViewHolder>()
        recycler_reviewers_new.adapter = adapter
        recycler_reviewers_new.layoutManager = LinearLayoutManager(activity)

        spinner_reviewers_new.setOnItemSelectedListener { view, position, id, item ->
            adapter.add(ReviewerItem(item.toString()))
        }

        button_create_new.setOnClickListener {
            val title = textlayout_title_new.editText?.text.toString()
            val co_chair = spinner_co_chair_new.getItems<String>().get(spinner_co_chair_new.selectedIndex)

            val reviewers = mutableListOf<String>()
            for (i in 0..adapter.itemCount - 1) {
                reviewers.add((adapter.getItem(i) as ReviewerItem).username)
            }

            val conference = Conference(title, co_chair, reviewers)

            viewModel.addConference(conference)

            val controller = NavHostFragment.findNavController(nav_host_fragment)
            controller.navigateUp()
        }

    }

}
