package ro.deepster.conferencemanagementsystem.view.roles

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import ro.deepster.conferencemanagementsystem.R


class RoleActivity : AppCompatActivity() {
    companion object {
        const val CONFERENCE_NAME = "Conference Name"
        val TAG = RoleActivity::class.java.canonicalName!!
        lateinit var conferenceName: String
    }

    //    lateinit var conference: Conference
    private lateinit var viewModel: RoleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role_activity)

        val controller = findNavController(this, R.id.role_content)
        // The activity will always be started by an Intent with Conference name extra
        conferenceName = intent.extras!!.getString(CONFERENCE_NAME)!!

        viewModel = ViewModelProviders.of(this).get(RoleViewModel::class.java)

        viewModel.currentConference.observe(this, Observer { conference ->

            // Show the conference title in the action bar
            title = conference.title
            viewModel.currentUser.observe(this, Observer { user ->
                when {
                    user.username == "admin" -> Log.d(TAG, "Starting admin")
                    //TODO Start admin
                    user.username == conference.co_chair -> Log.d(TAG, "Starting co-chair")
                    //TODO Start co-chair
                    user.username in conference.reviewers -> Log.d(TAG, "Starting reviewer")
                    //TODO Start reviewers
                    else -> {
                        Log.d(TAG, "Starting author")
                        controller.navigate(R.id.action_generic_to_author)
                    }
                }
            })
        })
    }


}
