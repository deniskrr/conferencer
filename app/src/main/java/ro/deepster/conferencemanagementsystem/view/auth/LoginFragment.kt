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
import kotlinx.android.synthetic.main.login_fragment.*
import ro.deepster.conferencemanagementsystem.R
import ro.deepster.conferencemanagementsystem.viewmodel.AuthViewModel

class LoginFragment : Fragment() {

    private lateinit var viewModel: AuthViewModel


    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity!!.run { ViewModelProviders.of(this).get(AuthViewModel::class.java) }

        viewModel.currentUser.observe(this, Observer<FirebaseUser> { currentUser ->
            if (currentUser != null) {
                NavHostFragment.findNavController(this).navigate(R.id.action_register_to_main)
            }
        })

        button_login.setOnClickListener {
            val email = edittext_email_login.text.toString()
            val password = edittext_password_login.text.toString()

            viewModel.loginUser(email, password)
        }

        text_no_account_login.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_login_to_register))

    }
}
