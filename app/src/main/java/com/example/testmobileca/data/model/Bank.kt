package com.example.testmobileca.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
class Bank(
    @Json(name = "name") var name: String,
    @Json(name = "isCA") var isCA: String,
    @Json(name = "accounts") var accounts: List<Account>,
) : Parcelable {
}