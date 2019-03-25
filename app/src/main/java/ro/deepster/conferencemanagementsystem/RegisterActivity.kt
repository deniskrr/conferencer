package ro.deepster.conferencemanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        button_register.setOnClickListener {
            val username = edittext_username_register.text.toString()
            val email = edittext_email_register.text.toString()
            val password = edittext_password_register.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(baseContext, "Successfully signed up", Toast.LENGTH_LONG)
                            .show()
                        val user = auth.currentUser

                        // TODO Start main activity
                    } else {
                        Toast.makeText(baseContext, "Sign-up failed", Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }


    }
}
