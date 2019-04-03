package ro.deepster.conferencemanagementsystem.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {
    companion object {
        val TAG = LoginActivity::class.java.simpleName
    }

    private lateinit var userModel: UserViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialise Firebase components
        auth = FirebaseAuth.getInstance()

        // Initialise user view model
        userModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        button_login.setOnClickListener {
            val email = edittext_email_login.text.toString()
            val password = edittext_password_login.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    Toast.makeText(baseContext, "Successfully logged in", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Toast.makeText(baseContext, "Log in failed", Toast.LENGTH_SHORT).show()
                }

        }

        text_no_account_login.setOnClickListener {
            val intent = Intent(baseContext, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
