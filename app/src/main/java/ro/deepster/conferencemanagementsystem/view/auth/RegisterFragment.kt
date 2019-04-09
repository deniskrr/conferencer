package ro.deepster.conferencemanagementsystem.view.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
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

        button_register.setOnClickListener {
            val username = edittext_username_register.text.toString()

            if (username.isBlank()) {
                return@setOnClickListener
            }

            val email = edittext_email_register.text.toString()
            val password = edittext_password_register.text.toString()

            viewModel.registerUser(username, email, password)
        }


        text_account_register.setOnClickListener {
            val navController = findNavController(it)
            navController.navigate(R.id.action_register_to_login)
        }


    }

}
