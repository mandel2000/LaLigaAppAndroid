package es.upsa.mimo.android.laligaapp.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import es.upsa.mimo.android.laligaapp.model.players.PlayersResponse
import es.upsa.mimo.android.laligaapp.model.teams.TeamsResponse
import es.upsa.mimo.android.laligaapp.network.ApiState
import es.upsa.mimo.android.laligaapp.utils.JsonUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FreeTeamsRepository @Inject constructor (@ApplicationContext private val context: Context) : TeamRepository {

    override suspend fun getTeams(league: Int, season: Int): Flow<ApiState<TeamsResponse>> {
        return flow {
            val teamsResponse = JsonUtils.parseJsonToModel(context, "LaLigaTeams.json", TeamsResponse::class.java)
            emit(ApiState.success(teamsResponse))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTeamDetail(league: Int, team: Int, season: Int):Flow<ApiState<TeamsResponse>>{
        return flow {
            val teamsResponse = JsonUtils.parseJsonToModel(context, "LaLigaTeams.json", TeamsResponse::class.java)
            emit(ApiState.success(teamsResponse))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTeamPlayers(team: Int, season: Int, page: Int,):Flow<ApiState<PlayersResponse>>{
        return flow {
            val playersResponse = JsonUtils.parseJsonToModel(context, "Players.json", PlayersResponse::class.java)
            emit(ApiState.success(playersResponse))
        }.flowOn(Dispatchers.IO)
    }
}