package ro.deepster.conferencemanagementsystem.view.roles

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.model.Conference
import ro.deepster.conferencemanagementsystem.model.User
import ro.deepster.conferencemanagementsystem.viewmodel.RoleViewModel


class RoleActivity : AppCompatActivity() {
    companion object {
        val CONFERENCE_NAME = "Conference Name"
        val TAG = RoleActivity::class.java.canonicalName
    }

    lateinit var user: User
    lateinit var conference: Conference

    //    lateinit var conference: Conference
    private lateinit var viewModel: RoleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role_activity)

        val controller = findNavController(this, R.id.role_content)
        // The activity will always be started by an Intent with Conference name extra
        val conferenceName = intent.extras!!.getString(CONFERENCE_NAME)!!

        viewModel = ViewModelProviders.of(this).get(RoleViewModel::class.java)

        viewModel.setConference(conferenceName)

        viewModel.currentConference.observe(this, Observer { newConference ->
            conference = newConference

            // Show the conference title in the action bar
            title = conference.title
            viewModel.currentUser.observe(this, Observer { newUser ->
                user = newUser
                if (user.username == "admin") {
                    Log.d(TAG, "Starting admin")
                    //TODO Start admin
                } else if (user.username == conference.co_chair) {
                    Log.d(TAG, "Starting co-chair")
                    //TODO Start co-chair
                } else if (user.username in conference.reviewers) {
                    Log.d(TAG, "Starting reviewer")
                    //TODO Start reviewers
                } else {
                    Log.d(TAG, "Starting author")
                    controller.navigate(R.id.action_generic_to_author)
                }
            })
        })
    }


}
