package es.upsa.mimo.android.laligaapp.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val headers: Map<String, String>) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .apply {
                headers.forEach { (key, value) ->
                    addHeader(key, value)
                }
            }
            .build()
        return chain.proceed(request)
    }
}