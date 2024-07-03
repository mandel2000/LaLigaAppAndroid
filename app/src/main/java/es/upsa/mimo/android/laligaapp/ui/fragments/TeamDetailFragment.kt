package es.upsa.mimo.android.laligaapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.adapters.PlayerListAdapter
import es.upsa.mimo.android.laligaapp.db.entities.TeamEntity
import es.upsa.mimo.android.laligaapp.model.players.PlayersResponse
import es.upsa.mimo.android.laligaapp.network.Status
import es.upsa.mimo.android.laligaapp.viewmodel.PlayersViewModel
import es.upsa.mimo.android.laligaapp.viewmodel.SharedViewModel
import es.upsa.mimo.android.laligaapp.viewmodel.TeamsViewModel
import kotlinx.coroutines.launch

class TeamDetailFragment : Fragment(R.layout.fragment_team_detail){

    private val args: TeamDetailFragmentArgs by navArgs()

    private lateinit var teamsViewModel: TeamsViewModel
    private lateinit var playersViewModel: PlayersViewModel
    private lateinit var playerListAdapter: PlayerListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemId = args.teamId

        teamsViewModel = ViewModelProvider(this)[TeamsViewModel::class.java]

        teamsViewModel.getTeamsDetails(140, itemId, 2023)

        val teamBadge : ImageView = view.findViewById(R.id.teamBadge)
        val teamName: TextView = view.findViewById(R.id.teamName)
        val city: TextView = view.findViewById(R.id.city)


        lifecycleScope.launch {
            teamsViewModel.teamsState.collect{
                when(it.status){
                    Status.LOADING ->{
                        //TODO
                    }
                    Status.SUCCESS -> {
                        it.data?.let { teamsResponse ->
                            val teamDetails = teamsResponse.teamsResp[0];
                            teamName.text = teamDetails.team?.name
                            city.text = teamDetails.venue?.city + ", " + teamDetails.team?.country
                            Glide.with(view).load(teamDetails.team?.logo).into(teamBadge)

                            val starButton: ToggleButton = view.findViewById(R.id.favButton)
                            starButton.setOnCheckedChangeListener { _, isChecked ->
                                val teamEntity = teamDetails.team?.toTeamEntity()

                                if (isChecked) {
                                    deleteTeam(teamEntity?.id)
                                    Toast.makeText(
                                        context, "Equipo " + teamDetails.team?.name + " añadido a favoritos",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    insertTeam(teamEntity)
                                    Toast.makeText(
                                        context, "Equipo " + teamDetails.team?.name + " eliminado de favoritos",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            getTeamPlayers(itemId, 2023, view)
                        }
                    }
                    Status.ERROR ->{
                        //TODO
                    }

                }
            }
        }



    }

    private fun insertTeam(teamEntity: TeamEntity?) {
        if(null != teamEntity){
            val sharedViewModel: SharedViewModel by activityViewModels()
            sharedViewModel.database.teamDao().let { teamDao ->
                lifecycleScope.launch {
                    teamDao.insertTeam(teamEntity)
                }
            }
        }
    }

    private fun deleteTeam(id: Int?) {
        if(null != id){
            val sharedViewModel: SharedViewModel by activityViewModels()
            sharedViewModel.database.teamDao().let { teamDao ->
                lifecycleScope.launch {
                    teamDao.deleteTeam(id)
                }
            }
        }
    }

    private fun getTeamPlayers(teamId: Int, season: Int, view: View){
        playersViewModel = ViewModelProvider(this)[PlayersViewModel::class.java]
        playersViewModel.loadPlayers(teamId, 2023)

        val playersListView : RecyclerView = view.findViewById(R.id.playerList)
        playersListView.layoutManager = LinearLayoutManager(requireContext());

        lifecycleScope.launch {
            playersViewModel.playersState.collect{
                when (it.status) {
                    Status.LOADING ->{

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

    private fun configureOnScrollPlayerList(playersResponse: PlayersResponse, playersListView : RecyclerView, teamId : Int){

        val totalPages = playersResponse.paging?.total

        playersListView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                val currentPage = playersResponse.paging?.current

                if (lastVisibleItemPosition == totalItemCount - 1 && totalPages != null && totalPages>currentPage!!) {
                    playersViewModel.loadMorePlayers(teamId, 2023)
                }
            }
        })

        lifecycleScope.launch {
            playersViewModel.playersState.collect{
                when (it.status) {
                    Status.LOADING ->{

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