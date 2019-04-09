package ro.deepster.conferencemanagementsystem.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.view.auth.AuthActivity

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase components
        auth = FirebaseAuth.getInstance()

        checkLoginStatus()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_sign_out -> {
                auth.signOut()
                checkLoginStatus()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkLoginStatus() {
        if (auth.currentUser == null) {
            val intent = Intent(baseContext, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
