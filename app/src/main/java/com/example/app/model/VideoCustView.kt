package com.example.app.model

import android.os.Parcel
import android.os.Parcelable

data class VideoCustView(val title : String,
                         val description : String,
                         val channel : String,
                         val thumbnail : String,
                         val id : String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(channel)
        parcel.writeString(thumbnail)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VideoCustView> {
        override fun createFromParcel(parcel: Parcel): VideoCustView {
            return VideoCustView(parcel)
        }

        override fun newArray(size: Int): Array<VideoCustView?> {
            return arrayOfNulls(size)
        }
    }
}