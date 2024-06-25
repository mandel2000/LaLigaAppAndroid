package es.upsa.mimo.android.laligaapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val API_KEY = "e4d01e5334msh9db6a390fbc8185p1360a1jsnb8cda4c74309"
    private const val API_HOST = "api-football-v1.p.rapidapi.com"
    private const val API_URL = "https://api-football-v1.p.rapidapi.com/v3"

    val headers = mapOf(
        "X-RapidApi-Key" to API_KEY,
        "X-RapidApi-Host" to API_HOST
    )
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HeaderInterceptor(headers)).build())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }


}