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

    // Create a Repository and pass the api
    // service we created in AppConfig file
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


    // Function to get new Comments
    fun getTeams(league: Int, season: Int) {

        // Since Network Calls takes time,Set the
        // initial value to loading state
        teamsState.value = ApiState.loading()

        // ApiCalls takes some time, So it has to be
        // run and background thread. Using viewModelScope
        // to call the api
        viewModelScope.launch {

            // Collecting the data emitted
            // by the function in repository
            repository.getTeams(league, season)
                // If any errors occurs like 404 not found
                // or invalid query, set the state to error
                // State to show some info
                // on screen
                .catch {
                    teamsState.value =
                        ApiState.error(it.message.toString())
                }
                // If Api call is succeeded, set the State to Success
                // and set the response data to data received from api
                .collect {
                    teamsState.value = ApiState.success(it.data)
                }
        }
    }
}