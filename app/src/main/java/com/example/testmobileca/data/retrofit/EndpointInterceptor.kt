package com.example.testmobileca.data.retrofit

import android.content.Context
import com.example.testmobileca.BuildConfig
import com.example.testmobileca.utils.DataStoreManager
import com.example.testmobileca.utils.isNetworkAvailable
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class EndpointInterceptor(
    private val dataStoreManager: DataStoreManager,
    @param:ApplicationContext private val context: Context
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()

        val url = request.url.toString()
        if (url.contains(BuildConfig.BASE_URL)) {
            if (context.isNetworkAvailable) {
                request = request.newBuilder()
                    .method(request.method, request.body)
                    .addHeader("Authorization", "Bearer " + dataStoreManager.getSyncData("userToken",""))
                    .build()
            } else {
                throw NetworkNotFoundException()
            }
        }

        return chain.proceed(request)
    }

    class NetworkNotFoundException : IOException("No network available")
}