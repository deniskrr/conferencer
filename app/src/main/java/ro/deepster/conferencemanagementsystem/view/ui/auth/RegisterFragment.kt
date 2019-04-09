package ro.deepster.conferencemanagementsystem.view.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.register_fragment.*
import ro.deepster.conferencemanagementsystem.R

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        // Initialize Firebase components
        auth = FirebaseAuth.getInstance()


        button_register.setOnClickListener {
            val username = edittext_username_register.text.toString()

            if (username.isBlank()) {
                return@setOnClickListener
            }

            val email = edittext_email_register.text.toString()
            val password = edittext_password_register.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(activity, "Successfully signed up", Toast.LENGTH_LONG)
                            .show()

                        // Write user to database
                        val userId = auth.currentUser!!.uid
                        viewModel.writeNewUser(userId, username, email)

                    } else {
                        Toast.makeText(activity, "Sign-up failed", Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }


    }

}
