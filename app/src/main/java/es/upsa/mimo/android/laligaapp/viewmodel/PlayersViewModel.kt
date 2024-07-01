package es.upsa.mimo.android.laligaapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.upsa.mimo.android.laligaapp.model.players.PlayersResponse
import es.upsa.mimo.android.laligaapp.model.teams.TeamsResponse
import es.upsa.mimo.android.laligaapp.network.ApiClient
import es.upsa.mimo.android.laligaapp.network.ApiState
import es.upsa.mimo.android.laligaapp.network.Status
import es.upsa.mimo.android.laligaapp.repository.TeamsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PlayersViewModel : ViewModel(){

    private val repository = TeamsRepository(
        ApiClient.apiService
    )

    val playersState = MutableStateFlow(
        ApiState(
            Status.LOADING,
            PlayersResponse(), ""
        )
    )

    init {

        getTeamPlayers(1,140, 2023)
    }

    fun getTeamPlayers(team: Int, season:Int,  page: Int) {

        playersState.value = ApiState.loading()

        viewModelScope.launch {


            repository.getTeamPlayers(team,season, page)
                .catch {
                    playersState.value =
                        ApiState.error(it.message.toString())
                }

                .collect {
                    playersState.value = ApiState.success(it.data)
                }
        }
    }
}