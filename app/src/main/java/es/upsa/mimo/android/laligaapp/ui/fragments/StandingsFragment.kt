package es.upsa.mimo.android.laligaapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.network.ApiClient
import kotlinx.coroutines.launch

class StandingsFragment : Fragment(R.layout.fragment_standings){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val response = ApiClient.apiService.getStandings(league = 140, season = 2023)

            if (response.isSuccessful) {
                val standingsData = response.body()
                standingsData?.standingsResp?.forEach { resp -> Log.d("StandingsFragment", "Standings data: ${resp}")}

            } else {
                // Handle API error
                Log.e("StandingsFragment", "Error: ${response.message()}")
            }


        }
    }
}