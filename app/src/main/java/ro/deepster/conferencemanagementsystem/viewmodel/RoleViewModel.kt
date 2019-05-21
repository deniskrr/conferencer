package ro.deepster.conferencemanagementsystem.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import ro.deepster.conferencemanagementsystem.model.Conference
import ro.deepster.conferencemanagementsystem.model.Proposal
import ro.deepster.conferencemanagementsystem.model.User
import java.util.*

class RoleViewModel : ViewModel() {

    val storage: FirebaseStorage = FirebaseStorage.getInstance()

    val currentConference: MutableLiveData<Conference> by lazy {
        MutableLiveData<Conference>()
    }.apply { Conference() }

    val currentUser: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }.apply { User() }

    init {
        FirebaseFirestore.getInstance().collection("users")
            .whereEqualTo("uid", FirebaseAuth.getInstance().currentUser!!.uid)
            .addSnapshotListener { documentSnapshot, _ ->
                currentUser.value = documentSnapshot?.toObjects(User::class.java)?.get(0)
            }
    }

    fun setConference(conferenceName: String) {
        FirebaseFirestore.getInstance().collection("conferences").document(conferenceName)
            .addSnapshotListener { documentSnapshot, _ ->
                currentConference.value = documentSnapshot?.toObject(Conference::class.java)
            }
    }

    fun addProposal(
        conference: Conference,
        title: String,
        topics: String,
        keywords: String,
        author: String,
        selectedPaper: Uri?
    ) {
        if (selectedPaper != null) { // Should upload paper
            val metadata = StorageMetadata.Builder()
                .setContentType("application/pdf")
                .build()

            val reference = storage.getReference("/papers/${UUID.randomUUID()}")

            reference.putFile(selectedPaper, metadata)
                .addOnSuccessListener {
                    reference.downloadUrl.addOnSuccessListener {
                        val proposal = Proposal(title, topics, keywords, author, it.toString())
                        conference.addProposal(proposal)
                        FirebaseFirestore.getInstance().collection("conferences").document(conference.title)
                            .set(conference)
                    }
                }
        } else {
            val proposal = Proposal(title, topics, keywords, author)
            conference.addProposal(proposal)
            FirebaseFirestore.getInstance().collection("conferences").document(conference.title).set(conference)
        }
    }

}
