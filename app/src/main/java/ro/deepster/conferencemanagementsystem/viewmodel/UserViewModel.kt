package ro.deepster.conferencemanagementsystem.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import ro.deepster.conferencemanagementsystem.model.User

class UserViewModel : ViewModel() {
    companion object {
        val TAG = UserViewModel::class.java.simpleName
    }

    private val database = FirebaseFirestore.getInstance()
    private val usersRef = database.collection("users")

    val userSnapshot: MutableLiveData<QuerySnapshot> by lazy {
        MutableLiveData<QuerySnapshot>()
    }

    init {
        usersRef.addSnapshotListener(EventListener<QuerySnapshot> { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed: $e")
                return@EventListener
            }

            val source = if (snapshot != null && snapshot.metadata.hasPendingWrites())
                "Local"
            else
                "Server"

            if (snapshot != null) userSnapshot.value = snapshot
        })
    }

    fun writeNewUser(userId: String, username: String, email: String) {
        val user = User(userId, username, email)
        database.collection("users").document(username)
            .set(user)
    }

    fun getUser(userId: String): User? {
        var user: User? = null
        usersRef.document("$userId").get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    user = document.toObject(User::class.java)
                }
            }
        return user
    }
}