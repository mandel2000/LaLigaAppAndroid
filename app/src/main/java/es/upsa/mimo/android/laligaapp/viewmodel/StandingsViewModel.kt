package es.upsa.mimo.android.laligaapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.upsa.mimo.android.laligaapp.model.standings.StandingsResponse
import es.upsa.mimo.android.laligaapp.network.ApiClient
import es.upsa.mimo.android.laligaapp.network.ApiState
import es.upsa.mimo.android.laligaapp.network.Status
import es.upsa.mimo.android.laligaapp.repository.StandingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class StandingsViewModel : ViewModel(){

    private val repository = StandingsRepository(ApiClient.apiService)

    val standingsState = MutableStateFlow(
        ApiState(Status.LOADING, StandingsResponse(), "")
    )

    init {
        getStandings(140, 2023)
    }

    fun getStandings(leagueId: Int, season: Int){

        standingsState.value = ApiState.loading()

        viewModelScope.launch {
            repository.getStandings(leagueId, season)
                .catch {
                    standingsState.value = ApiState.error(it.message.toString())
                }
                .collect{
                    standingsState.value = ApiState.success(it.data)
                }
        }
    }
}