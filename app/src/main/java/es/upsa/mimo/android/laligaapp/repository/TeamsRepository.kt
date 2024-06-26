package es.upsa.mimo.android.laligaapp.repository

import es.upsa.mimo.android.laligaapp.model.teams.TeamsResponse
import es.upsa.mimo.android.laligaapp.network.ApiService
import es.upsa.mimo.android.laligaapp.network.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TeamsRepository (private val apiService: ApiService){

    suspend fun getTeam(league: Int, season: Int): Flow<ApiState<TeamsResponse>> {
        return flow {

            // get the comment Data from the api
            val comment=apiService.getTeams(league, season)

            // Emit this data wrapped in
            // the helper class [CommentApiState]
            emit(ApiState.success(comment))
        }.flowOn(Dispatchers.IO)
    }
}