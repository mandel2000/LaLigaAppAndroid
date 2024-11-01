package es.upsa.mimo.android.laligaapp.repository

import es.upsa.mimo.android.laligaapp.model.standings.StandingsResponse
import es.upsa.mimo.android.laligaapp.network.ApiService
import es.upsa.mimo.android.laligaapp.network.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StandingsRepository @Inject constructor (private val apiService: ApiService) : StandingRepository{

    override suspend fun getStandings(league: Int, season: Int): Flow<ApiState<StandingsResponse>> {
        return flow{
            val standings = apiService.getStandings(league, season)
            emit(ApiState.success(standings))
        }.flowOn(Dispatchers.IO)
    }

}