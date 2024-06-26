package es.upsa.mimo.android.laligaapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.upsa.mimo.android.laligaapp.model.fixtures.FixturesResponse
import es.upsa.mimo.android.laligaapp.network.ApiClient
import es.upsa.mimo.android.laligaapp.network.ApiState
import es.upsa.mimo.android.laligaapp.network.Status
import es.upsa.mimo.android.laligaapp.repository.FixturesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FixturesViewModel : ViewModel(){

    private val fixturesRepository = FixturesRepository(
        ApiClient.apiService
    )

    val fixturesState = MutableStateFlow(
        ApiState(
            Status.LOADING,
            FixturesResponse(),
            ""
        )
    )

    init {
        getFixtures(140, 2023)
    }

    fun getFixtures(league: Int, season: Int){

        fixturesState.value = ApiState.loading()

        viewModelScope.launch {
            fixturesRepository.getFixtures(league, season)
                .catch {
                    fixturesState.value = ApiState.error(it.message.toString())
                }
                .collect {
                    fixturesState.value = ApiState.success(it.data)
                }
        }
    }
}