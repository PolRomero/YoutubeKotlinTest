package com.example.app.model

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class HistoryView (val history : ArrayList<VideoCustView> = ArrayList()): Parcelable {


    constructor(parcel: Parcel) : this(parcel.readArrayList(VideoCustView::class.java.classLoader) as ArrayList<VideoCustView> )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeList(history)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HistoryView> {
        override fun createFromParcel(parcel: Parcel): HistoryView {
            return HistoryView(parcel)
        }

        override fun newArray(size: Int): Array<HistoryView?> {
            return arrayOfNulls(size)
        }
    }

}