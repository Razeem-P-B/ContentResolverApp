package com.example.contentresolverapp.models

import android.os.Parcel
import android.os.Parcelable

data class PersonalData(

    var bs: String? = "",
    var catchPhrase: String? = "",
    var companyName: String? = "",
    var email: String? = "",
    var id: Int = 0,
    var name: String? = "",
    var phone: String? = "",
    var profileImage: String? = "",
    var username: String? = "",
    var website: String? = "",
    var lat: String? = "",
    var lng: String? = "",
    var city: String? = "",
    var street: String? = "",
    var suite: String? = "",
    var zipcode: String? = ""
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(bs)
        parcel.writeString(catchPhrase)
        parcel.writeString(companyName)
        parcel.writeString(email)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(profileImage)
        parcel.writeString(username)
        parcel.writeString(website)
        parcel.writeString(lat)
        parcel.writeString(lng)
        parcel.writeString(city)
        parcel.writeString(street)
        parcel.writeString(suite)
        parcel.writeString(zipcode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonalData> {
        override fun createFromParcel(parcel: Parcel): PersonalData {
            return PersonalData(parcel)
        }

        override fun newArray(size: Int): Array<PersonalData?> {
            return arrayOfNulls(size)
        }
    }
}





