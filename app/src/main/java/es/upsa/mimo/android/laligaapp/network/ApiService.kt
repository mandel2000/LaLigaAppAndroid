package es.upsa.mimo.android.laligaapp.network

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

        @GET("data")
        fun getData(): Flow<Response<List<DataItem>>>

}