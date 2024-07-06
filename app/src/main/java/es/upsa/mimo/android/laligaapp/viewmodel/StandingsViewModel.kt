package es.upsa.mimo.android.laligaapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.upsa.mimo.android.laligaapp.BuildConfig
import es.upsa.mimo.android.laligaapp.di.Free
import es.upsa.mimo.android.laligaapp.di.Paid
import es.upsa.mimo.android.laligaapp.model.standings.StandingsResponse
import es.upsa.mimo.android.laligaapp.network.ApiState
import es.upsa.mimo.android.laligaapp.network.Status
import es.upsa.mimo.android.laligaapp.repository.StandingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandingsViewModel @Inject constructor(@Free private val freeStandingsRepository: StandingRepository?,
                                             @Paid private val paidStandingsRepository: StandingRepository?
): ViewModel(){

    private val repository = if(BuildConfig.FLAVOR == "free") freeStandingsRepository else paidStandingsRepository

    val standingsState = MutableStateFlow(
        ApiState(Status.LOADING, StandingsResponse(), "")
    )

    init {
        getStandings(140, 2023)
    }

    fun getStandings(leagueId: Int, season: Int){

        standingsState.value = ApiState.loading()

        viewModelScope.launch {
            repository!!.getStandings(leagueId, season)
                .catch {
                    standingsState.value = ApiState.error(it.message.toString())
                }
                .collect{
                    standingsState.value = ApiState.success(it.data)
                }
        }
    }
}