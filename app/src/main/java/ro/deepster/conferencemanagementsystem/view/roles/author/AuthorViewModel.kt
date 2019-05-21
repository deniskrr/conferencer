package ro.deepster.conferencemanagementsystem.view.roles.author

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageMetadata
import ro.deepster.conferencemanagementsystem.model.Proposal
import ro.deepster.conferencemanagementsystem.view.roles.RoleViewModel
import java.util.*

class AuthorViewModel : RoleViewModel() {


    fun addProposal(
        title: String,
        abstract: String,
        topics: String,
        keywords: String,
        selectedPaper: Uri?
    ) {
        val author = currentUser.value!!.username
        val conference = currentConference.value!!
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
            val proposal = Proposal(title, abstract, topics, keywords, author)
            conference.addProposal(proposal)
            FirebaseFirestore.getInstance().collection("conferences").document(conference.title).set(conference)
        }
    }
}