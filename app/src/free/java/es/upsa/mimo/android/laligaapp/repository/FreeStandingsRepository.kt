package es.upsa.mimo.android.laligaapp.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import es.upsa.mimo.android.laligaapp.model.standings.StandingsResponse
import es.upsa.mimo.android.laligaapp.network.ApiState
import es.upsa.mimo.android.laligaapp.utils.JsonUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FreeStandingsRepository @Inject constructor (@ApplicationContext private val context: Context) : StandingRepository{

    override suspend fun getStandings(league: Int, season: Int): Flow<ApiState<StandingsResponse>> {
        return flow{
            val standingsResponse = JsonUtils.parseJsonToModel(context , "LaLigaStandings.json", StandingsResponse::class.java)
            emit(ApiState.success(standingsResponse))
        }.flowOn(Dispatchers.IO)
    }

}