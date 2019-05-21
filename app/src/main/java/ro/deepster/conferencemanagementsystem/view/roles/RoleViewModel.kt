package ro.deepster.conferencemanagementsystem.view.roles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import ro.deepster.conferencemanagementsystem.model.Conference
import ro.deepster.conferencemanagementsystem.model.User

open class RoleViewModel : ViewModel() {

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

        FirebaseFirestore.getInstance().collection("conferences").document(RoleActivity.conferenceName)
            .addSnapshotListener { documentSnapshot, _ ->
                currentConference.value = documentSnapshot?.toObject(Conference::class.java)
            }
    }


}
