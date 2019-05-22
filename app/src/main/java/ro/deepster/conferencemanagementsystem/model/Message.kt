package ro.deepster.conferencemanagementsystem.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Message(val sender: String = "", val text: String = "") : Parcelable
