package ro.deepster.conferencemanagementsystem.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import ro.deepster.conferencemanagementsystem.R

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_activity)

        val navController = findNavController(this, R.id.main_content)



    }

}
