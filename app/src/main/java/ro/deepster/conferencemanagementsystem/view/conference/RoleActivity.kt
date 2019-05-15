package ro.deepster.conferencemanagementsystem.view.conference

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ro.deepster.conferencemanagementsystem.model.Conference
import ro.deepster.conferencemanagementsystem.model.User
import ro.deepster.conferencemanagementsystem.viewmodel.RoleViewModel


class RoleActivity : AppCompatActivity() {
    companion object {
        val CONFERENCE_NAME = "Conference Name"
        val TAG = RoleActivity::class.java.canonicalName
    }

    private lateinit var user: User
    private lateinit var conference: Conference

    //    lateinit var conference: Conference
    private lateinit var viewModel: RoleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ro.deepster.conferencemanagementsystem.R.layout.activity_role_activity)

        // The activity will always be started by an Intent with Conference name extra
        val conferenceName = intent.extras!!.getString(CONFERENCE_NAME)!!

        viewModel = ViewModelProviders.of(this).get(RoleViewModel::class.java)

        viewModel.setConference(conferenceName)

        viewModel.currentConference.observe(this, Observer { newConference ->
            conference = newConference
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
                }
            })
        })
    }


}
