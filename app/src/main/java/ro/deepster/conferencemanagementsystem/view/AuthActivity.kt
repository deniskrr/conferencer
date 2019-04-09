package ro.deepster.conferencemanagementsystem.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.view.ui.auth.RegisterFragment

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RegisterFragment.newInstance())
                .commitNow()
        }
    }

}
