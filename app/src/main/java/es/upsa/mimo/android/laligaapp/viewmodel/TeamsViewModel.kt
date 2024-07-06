package es.upsa.mimo.android.laligaapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.upsa.mimo.android.laligaapp.BuildConfig
import es.upsa.mimo.android.laligaapp.di.Free
import es.upsa.mimo.android.laligaapp.di.Paid
import es.upsa.mimo.android.laligaapp.model.teams.TeamsResponse
import es.upsa.mimo.android.laligaapp.network.ApiState
import es.upsa.mimo.android.laligaapp.network.Status
import es.upsa.mimo.android.laligaapp.repository.TeamRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(@Free private val freeTeamRepository: TeamRepository?,
                                         @Paid private val paidTeamRepository: TeamRepository?) : ViewModel() {

    private val repository = if(BuildConfig.FLAVOR == "free") freeTeamRepository else paidTeamRepository

    val teamsState = MutableStateFlow(
        ApiState(
            Status.LOADING,
            TeamsResponse(), ""
        )
    )

    init {

        getTeams(140, 2023)
    }


    fun getTeams(league: Int, season: Int) {

        teamsState.value = ApiState.loading()

        viewModelScope.launch {


            repository!!.getTeams(league, season)
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


            repository!!.getTeamDetail(league,id, season)
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