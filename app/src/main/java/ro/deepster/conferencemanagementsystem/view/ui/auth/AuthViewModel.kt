package ro.deepster.conferencemanagementsystem.view.ui.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import ro.deepster.conferencemanagementsystem.model.User

class AuthViewModel : ViewModel() {

    private val database = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private

    fun writeNewUser(userId: String, username: String, email: String) {
        val user = User(userId, username, email)
        database.collection("users").document(userId)
            .set(user)
    }


}
