package es.upsa.mimo.android.laligaapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.upsa.mimo.android.laligaapp.BuildConfig
import es.upsa.mimo.android.laligaapp.di.Free
import es.upsa.mimo.android.laligaapp.di.Paid
import es.upsa.mimo.android.laligaapp.model.players.PlayersResponse
import es.upsa.mimo.android.laligaapp.network.ApiClient
import es.upsa.mimo.android.laligaapp.network.ApiState
import es.upsa.mimo.android.laligaapp.network.Status
import es.upsa.mimo.android.laligaapp.repository.TeamRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PlayersViewModel @Inject constructor(@Free private val freePlayersRepository: TeamRepository?,
                                           @Paid private val paidPlayersRepository: TeamRepository?
) : ViewModel(){

    private val repository = if(BuildConfig.FLAVOR == "free") freePlayersRepository else paidPlayersRepository


    val playersState = MutableStateFlow(
        ApiState(
            Status.LOADING,
            PlayersResponse(), ""
        )
    )

    private val currentPage = 1

    init {

        getTeamPlayers(1,2023, currentPage)
    }

    fun loadPlayers(teamId:Int, season: Int){
        getTeamPlayers(teamId,season, currentPage)
    }

    fun loadMorePlayers(teamId:Int, season: Int){

        getTeamPlayers(teamId,season, currentPage+1)
    }

    fun getTeamPlayers(team: Int, season:Int,  page: Int) {

        playersState.value = ApiState.loading()

        viewModelScope.launch {


            repository!!.getTeamPlayers(team,season, page)
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