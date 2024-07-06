package es.upsa.mimo.android.laligaapp.repository

import es.upsa.mimo.android.laligaapp.model.fixtures.FixturesResponse
import es.upsa.mimo.android.laligaapp.network.ApiState
import kotlinx.coroutines.flow.Flow

interface FixtureRepository {

    suspend fun getFixtures(league: Int, season: Int): Flow<ApiState<FixturesResponse>>
}