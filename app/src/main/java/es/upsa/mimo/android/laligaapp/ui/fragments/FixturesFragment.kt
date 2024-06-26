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

class FixturesFragment : Fragment(R.layout.fragment_fixtures){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fixtures, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val response = ApiClient.apiService.getFixtures(league = 140, season = 2023)

//            if (response.isSuccessful) {
//                val fixturesData = response.body()
//                fixturesData?.fixtureResp?.forEach { resp -> Log.d("FixturesFragment", "Fixture data: ${resp}")}
//
//            } else {
//                // Handle API error
//                Log.e("FixturesFragment", "Error: ${response.message()}")
//            }


        }
    }
}