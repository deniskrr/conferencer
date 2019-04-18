package ro.deepster.conferencemanagementsystem.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import ro.deepster.conferencemanagementsystem.model.User

class AuthViewModel : ViewModel() {

    private val database = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance().also { auth ->
        auth.addAuthStateListener {
            currentUser.value = auth.currentUser
        }
    }
    val currentUser: MutableLiveData<FirebaseUser> by lazy {
        MutableLiveData<FirebaseUser>()
    }.apply { auth.currentUser }

    fun writeNewUser(userId: String, username: String, email: String, name: String, aff: String, web: String) {
        val user = User(userId, username, email, name, aff, web)
        database.collection("users").document(userId)
            .set(user)
    }

    fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }

    fun registerUser(username: String, email: String, password: String, name: String, aff: String, web: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = currentUser.value!!.uid
                    writeNewUser(userId, username, email, name, aff, web)
                }
            }
    }


}
