package ro.deepster.conferencemanagementsystem.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import ro.deepster.conferencemanagementsystem.model.Conference

class MainViewModel : ViewModel() {

    private val userSnapshot = FirebaseFirestore.getInstance().collection("users").apply {
        addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                return@addSnapshotListener
            }
            users.value = snapshot
        }
    }

    private val conferenceSnapshot = FirebaseFirestore.getInstance().collection("conferences").apply {
        addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                return@addSnapshotListener
            }
            conferences.value = snapshot
        }
    }

    val conferences: MutableLiveData<QuerySnapshot> by lazy {
        MutableLiveData<QuerySnapshot>()
    }.apply {
        conferenceSnapshot
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

    fun getConferences(): List<String> {
        val conferenceList = mutableListOf<String>()

        val documents = conferences.value?.documents
        if (documents != null) {
            for (conference in documents) {
                conferenceList.add(conference.id)
            }
        }

        return conferenceList
    }

    fun addConference(conference: Conference) {
        conferenceSnapshot.document(conference.title).set(conference)
    }


}
