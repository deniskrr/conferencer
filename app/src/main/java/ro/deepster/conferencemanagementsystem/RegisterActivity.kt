package ro.deepster.conferencemanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    private fun writeNewUser(userId: String, username: String, email: String) {
        val user = User(userId, username, email)
        database.reference.child(username).setValue(user)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase components
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        button_register.setOnClickListener {
            val username = edittext_username_register.text.toString()

            if (username.isBlank()) {
                return@setOnClickListener
            }

            val email = edittext_email_register.text.toString()
            val password = edittext_password_register.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(baseContext, "Successfully signed up", Toast.LENGTH_LONG)
                            .show()

                        // Write user to database
                        val userId = auth.currentUser!!.uid
                        writeNewUser(userId, username, email)

                        // TODO Start main activity
                    } else {
                        Toast.makeText(baseContext, "Sign-up failed", Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }


    }
}
