package ro.deepster.conferencemanagementsystem.view.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.register_fragment.*
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.viewmodel.AuthViewModel

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = activity!!.run { ViewModelProviders.of(this).get(AuthViewModel::class.java) }

        viewModel.currentUser.observe(this, Observer<FirebaseUser> { currentUser ->
            if (currentUser != null) {
                NavHostFragment.findNavController(this).navigate(R.id.action_register_to_main)
            }
        })

        button_register.setOnClickListener {
            val username = edittext_username_register.text.toString()

            if (username.isBlank()) {
                return@setOnClickListener
            }

            val email = edittext_email_register.text.toString()
            val password = edittext_password_register.text.toString()

            viewModel.registerUser(username, email, password)
        }


        text_account_register.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_register_to_login))


    }

}
