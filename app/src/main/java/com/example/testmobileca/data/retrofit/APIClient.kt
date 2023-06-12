package com.example.testmobileca.data.retrofit

import okhttp3.ResponseBody
import retrofit2.http.*

interface APIClient {

    @GET("banks.json")
    suspend fun getBanks(): ResponseBody

}