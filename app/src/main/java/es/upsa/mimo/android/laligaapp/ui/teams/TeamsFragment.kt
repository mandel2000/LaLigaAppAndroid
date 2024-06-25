package es.upsa.mimo.android.laligaapp.ui.teams

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TeamsFragment : Fragment(R.layout.fragment_teams){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Call your ApiService within a coroutine scope
        lifecycleScope.launch {
            val response = ApiClient.apiService.getTeams(league = 140, season = 2023)

            if (response.isSuccessful) {
                val teamData = response.body()
                teamData?.response?.forEach { resp -> Log.d("TeamsFragment", "Team data: ${resp.team}")}

            } else {
                // Handle API error
                Log.e("TeamsFragment", "Error: ${response.message()}")
            }


        }
    }



}