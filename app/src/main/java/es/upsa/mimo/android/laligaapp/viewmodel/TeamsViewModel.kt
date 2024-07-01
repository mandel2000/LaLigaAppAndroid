package es.upsa.mimo.android.laligaapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.upsa.mimo.android.laligaapp.model.teams.TeamsResponse
import es.upsa.mimo.android.laligaapp.network.ApiClient
import es.upsa.mimo.android.laligaapp.network.ApiState
import es.upsa.mimo.android.laligaapp.network.Status
import es.upsa.mimo.android.laligaapp.repository.TeamsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TeamsViewModel : ViewModel() {

    private val repository = TeamsRepository(
        ApiClient.apiService
    )

    val teamsState = MutableStateFlow(
        ApiState(
            Status.LOADING,
            TeamsResponse(), ""
        )
    )

    init {
        // Initiate a starting
        // search with comment Id 1
        getTeams(140, 2023)
    }


    fun getTeams(league: Int, season: Int) {

        teamsState.value = ApiState.loading()


        viewModelScope.launch {


            repository.getTeams(league, season)
                .catch {
                    teamsState.value =
                        ApiState.error(it.message.toString())
                }

                .collect {
                    teamsState.value = ApiState.success(it.data)
                }
        }
    }

    fun getTeamsDetails(league: Int, id:Int,  season: Int) {

        teamsState.value = ApiState.loading()

        viewModelScope.launch {


            repository.getTeamDetail(league,id, season)
                .catch {
                    teamsState.value =
                        ApiState.error(it.message.toString())
                }

                .collect {
                    teamsState.value = ApiState.success(it.data)
                }
        }
    }


}