package com.example.testmobileca.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Account(
    @PrimaryKey
    @Json(name = "id") var id: Long,
    @Json(name = "balance") var balance: String,
    @Json(name = "contract_number") var contractNumber: String?,
    @Json(name = "holder") var holder: String?,
    @Json(name = "label") var label: String,
    @Json(name = "operations") var operations: List<Operation>,
    @Json(name = "order") var order: String?,
    @Json(name = "product_code") var productCode: String?,
    @Json(name = "role") var role: String?,
) : Parcelable

