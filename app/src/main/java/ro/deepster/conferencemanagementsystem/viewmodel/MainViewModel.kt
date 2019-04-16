package ro.deepster.conferencemanagementsystem.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class MainViewModel : ViewModel() {

    private val userSnapshot = FirebaseFirestore.getInstance().collection("users").apply {
        addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                return@addSnapshotListener
            }
            users.value = snapshot
        }
    }

    val users: MutableLiveData<QuerySnapshot> by lazy {
        MutableLiveData<QuerySnapshot>()
    }.apply {
        userSnapshot
    }

    fun getUsers(): List<String> {
        val userList = mutableListOf<String>()

        val documents = users.value?.documents
        if (documents != null) {
            for (user in documents) {
                userList.add(user.id)
            }
        }

        return userList
    }


}
