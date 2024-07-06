package es.upsa.mimo.android.laligaapp.repository

import es.upsa.mimo.android.laligaapp.model.fixtures.FixturesResponse
import es.upsa.mimo.android.laligaapp.network.ApiService
import es.upsa.mimo.android.laligaapp.network.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FixturesRepository @Inject constructor (private val apiService: ApiService):FixtureRepository{

    override suspend fun getFixtures(league: Int, season: Int ): Flow<ApiState<FixturesResponse>> {
        return flow {

            val fixtures = apiService.getFixtures(league, season)

            emit(ApiState.success(fixtures))
        }.flowOn(Dispatchers.IO)
    }

}