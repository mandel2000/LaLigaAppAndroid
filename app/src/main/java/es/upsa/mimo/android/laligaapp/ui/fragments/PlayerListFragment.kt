package es.upsa.mimo.android.laligaapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.adapters.PlayerListAdapter
import es.upsa.mimo.android.laligaapp.model.players.PlayersResponse
import es.upsa.mimo.android.laligaapp.network.Status
import es.upsa.mimo.android.laligaapp.viewmodel.PlayersViewModel
import kotlinx.coroutines.launch
@AndroidEntryPoint
class PlayerListFragment() : Fragment() {

    private val playersViewModel: PlayersViewModel by viewModels()
    private lateinit var playerListAdapter: PlayerListAdapter
    private var teamId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            teamId = it.getInt("teamId")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.player_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTeamPlayers(teamId, 2023, view)

    }

    private fun getTeamPlayers(teamId: Int, season: Int, view: View) {
        playersViewModel.loadPlayers(teamId, 2023)

        val playersListView: RecyclerView = view.findViewById(R.id.playerList)
        playersListView.layoutManager = LinearLayoutManager(requireContext());

        lifecycleScope.launch {
            playersViewModel.playersState.collect {
                when (it.status) {
                    Status.LOADING -> {

                    }

                    Status.SUCCESS -> {
                        it.data?.let { playersResponse ->

                            Log.d("TEST PLAYERS", playersResponse.toString())
                            val playersData = playersResponse.playerResp
                            playerListAdapter = PlayerListAdapter(playersList = playersData)
                            playersListView.adapter = playerListAdapter
                            configureOnScrollPlayerList(playersResponse, playersListView, teamId)
                        }
                    }

                    Status.ERROR -> {

                    }
                }
            }
        }
    }

    private fun configureOnScrollPlayerList(
        playersResponse: PlayersResponse,
        playersListView: RecyclerView,
        teamId: Int
    ) {

        val totalPages = playersResponse.paging?.total

        playersListView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                val currentPage = playersResponse.paging?.current

                if (lastVisibleItemPosition == totalItemCount - 1 && totalPages != null && totalPages > currentPage!!) {
                    playersViewModel.loadMorePlayers(teamId, 2023)
                }
            }
        })

        lifecycleScope.launch {
            playersViewModel.playersState.collect {
                when (it.status) {
                    Status.LOADING -> {

                    }

                    Status.SUCCESS -> {
                        it.data?.let { playersResponse ->

                            Log.d("TEST PLAYERS", playersResponse.toString())
                            playerListAdapter.addPlayers(playersResponse.playerResp)

                        }
                    }

                    Status.ERROR -> {

                    }
                }
            }
        }

    }
}