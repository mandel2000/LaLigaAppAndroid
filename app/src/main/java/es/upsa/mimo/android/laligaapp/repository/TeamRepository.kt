package es.upsa.mimo.android.laligaapp.repository

import es.upsa.mimo.android.laligaapp.model.players.PlayersResponse
import es.upsa.mimo.android.laligaapp.model.teams.TeamsResponse
import es.upsa.mimo.android.laligaapp.network.ApiService
import es.upsa.mimo.android.laligaapp.network.ApiState
import kotlinx.coroutines.flow.Flow

interface TeamRepository {

    suspend fun getTeams(league: Int, season: Int): Flow<ApiState<TeamsResponse>>

    suspend fun getTeamDetail(league: Int, team: Int, season: Int): Flow<ApiState<TeamsResponse>>

    suspend fun getTeamPlayers(team: Int, season: Int, page: Int): Flow<ApiState<PlayersResponse>>
}