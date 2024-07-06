package es.upsa.mimo.android.laligaapp.repository

import es.upsa.mimo.android.laligaapp.model.standings.StandingsResponse
import es.upsa.mimo.android.laligaapp.network.ApiState
import kotlinx.coroutines.flow.Flow

interface StandingRepository {

    suspend fun getStandings(league: Int, season: Int): Flow<ApiState<StandingsResponse>>
}