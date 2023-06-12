package com.example.testmobileca.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "operations")
@JsonClass(generateAdapter = true)
class Operation(
    @PrimaryKey
    @Json(name = "id") var id: Long,
    @Json(name = "amount") var amount: String,
    @Json(name = "category") var category: String?,
    @Json(name = "date") var date: String,
    @Json(name = "title") var title: String,
) : Parcelable {
}