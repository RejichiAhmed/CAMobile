package com.example.testmobileca.data.retrofit

import com.example.testmobileca.data.model.response.BankResponse
import retrofit2.http.*

interface APIClient {

    @GET("banks.json")
    suspend fun getBanks(): BankResponse

}