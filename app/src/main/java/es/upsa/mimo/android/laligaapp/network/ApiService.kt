package es.upsa.mimo.android.laligaapp.network

import es.upsa.mimo.android.laligaapp.TeamsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

        @GET("teams")
        suspend fun getTeams(
                @Query("league") league: Int,
                @Query("season") season: Int
        ): Response<TeamsResponse>

}