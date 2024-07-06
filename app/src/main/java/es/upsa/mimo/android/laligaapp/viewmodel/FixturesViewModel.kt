package es.upsa.mimo.android.laligaapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.upsa.mimo.android.laligaapp.BuildConfig
import es.upsa.mimo.android.laligaapp.di.Free
import es.upsa.mimo.android.laligaapp.di.Paid
import es.upsa.mimo.android.laligaapp.model.fixtures.FixturesResponse
import es.upsa.mimo.android.laligaapp.network.ApiState
import es.upsa.mimo.android.laligaapp.network.Status
import es.upsa.mimo.android.laligaapp.repository.FixtureRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FixturesViewModel @Inject constructor(@Free private val freeFixturesRepository: FixtureRepository?,
                                            @Paid private val paidFixturesRepository: FixtureRepository?)
    : ViewModel(){

    private val repository = if(BuildConfig.FLAVOR == "free") freeFixturesRepository else paidFixturesRepository

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
            repository!!.getFixtures(league, season)
                .catch {
                    fixturesState.value = ApiState.error(it.message.toString())
                }
                .collect {
                    fixturesState.value = ApiState.success(it.data)
                }
        }
    }
}