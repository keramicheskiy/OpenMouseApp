package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class SettingsStorage(val status: String): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SettingsStorage> {
        override fun createFromParcel(parcel: Parcel): SettingsStorage {
            return SettingsStorage(parcel)
        }

        override fun newArray(size: Int): Array<SettingsStorage?> {
            return arrayOfNulls(size)
        }
    }


}