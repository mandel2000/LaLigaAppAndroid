package es.upsa.mimo.android.laligaapp.repository

import es.upsa.mimo.android.laligaapp.model.players.PlayersResponse
import es.upsa.mimo.android.laligaapp.model.teams.TeamsResponse
import es.upsa.mimo.android.laligaapp.network.ApiService
import es.upsa.mimo.android.laligaapp.network.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TeamsRepository @Inject constructor (private val apiService: ApiService) : TeamRepository {

    override suspend fun getTeams(league: Int, season: Int): Flow<ApiState<TeamsResponse>> {
        return flow {

            val teams=apiService.getTeams(league, season)

            emit(ApiState.success(teams))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTeamDetail(league: Int, team: Int, season: Int):Flow<ApiState<TeamsResponse>>{
        return flow {
            val teamDetail=apiService.getTeamDetail(league, team, season)
            emit(ApiState.success(teamDetail))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTeamPlayers(team: Int, season: Int, page: Int,):Flow<ApiState<PlayersResponse>>{
        return flow {
            val teamPlayers=apiService.getPlayers(team, season, page)
            emit(ApiState.success(teamPlayers))
        }.flowOn(Dispatchers.IO)
    }
}