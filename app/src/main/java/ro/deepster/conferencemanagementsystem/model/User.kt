package ro.deepster.conferencemanagementsystem.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val uid: String,
    var username: String,
    var email: String,
    var name: String,
    var affiliation: String,
    var web_page: String
) {

    constructor() : this("", "", "", "", "", "" ) {}

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "username" to username,
            "e-mail" to email,
            "name" to name,
            "affiliation" to affiliation,
            "web_page" to web_page

        )
    }


}