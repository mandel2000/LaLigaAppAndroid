package es.upsa.mimo.android.laligaapp.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import es.upsa.mimo.android.laligaapp.model.fixtures.FixturesResponse
import es.upsa.mimo.android.laligaapp.network.ApiState
import es.upsa.mimo.android.laligaapp.utils.JsonUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FreeFixturesRepository @Inject constructor ( @ApplicationContext private val context: Context)
    :FixtureRepository{

    override suspend fun getFixtures(league: Int, season: Int ): Flow<ApiState<FixturesResponse>> {
        return flow {
            val fixturesResponse = JsonUtils.parseJsonToModel(context, "LaLigaMatches.json", FixturesResponse::class.java)
            emit(ApiState.success(fixturesResponse))
        }.flowOn(Dispatchers.IO)
    }

}