package ro.deepster.conferencemanagementsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register.*
import ro.deepster.conferencemanagementsystem.domain.User

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseFirestore

    private fun writeNewUser(userId: String, username: String, email: String) {
        val user = User(userId, username, email)
        database.collection("users").document(username)
            .set(user)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase components
        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()

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

        text_account_register.setOnClickListener {
            val intent = Intent(baseContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
