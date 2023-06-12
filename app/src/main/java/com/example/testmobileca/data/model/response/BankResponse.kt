package com.example.testmobileca.data.model.response

import android.os.Parcelable
import com.example.testmobileca.data.model.Bank
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class BankResponse(
    @Json(name = "banks") val banks: List<Bank>,
    ) : Parcelable