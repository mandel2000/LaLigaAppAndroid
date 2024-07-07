package es.upsa.mimo.android.laligaapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import es.upsa.mimo.android.laligaapp.BuildConfig
import es.upsa.mimo.android.laligaapp.R
import es.upsa.mimo.android.laligaapp.db.entities.TeamEntity
import es.upsa.mimo.android.laligaapp.network.Status
import es.upsa.mimo.android.laligaapp.viewmodel.SharedViewModel
import es.upsa.mimo.android.laligaapp.viewmodel.TeamsViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TeamInfoFragment : Fragment(R.layout.fragment_team_info){

    private val teamsViewModel: TeamsViewModel by viewModels()

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
        return inflater.inflate(R.layout.fragment_team_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        teamsViewModel.getTeamsDetails(140, teamId, 2023)

        val teamBadge : ImageView = view.findViewById(R.id.teamBadge)
        val teamName: TextView = view.findViewById(R.id.teamName)
        val city: TextView = view.findViewById(R.id.city)
        val starButton: ToggleButton = view.findViewById(R.id.favButton)
        val stadium : TextView = view.findViewById(R.id.stadiumValue)
        val stadiumPhoto : ImageView = view.findViewById(R.id.stadiumPhoto)

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
                            stadium.text = teamDetails.venue?.name
                            Glide.with(view).load(teamDetails.venue?.image).into(stadiumPhoto)

                            val sharedViewModel: SharedViewModel by activityViewModels()
                            sharedViewModel.database.teamDao().let { teamDao ->
                                lifecycleScope.launch {
                                    val isFav = teamDao.existsById(teamDetails.team?.id!!)
                                    starButton.isChecked = isFav
                                }
                            }

                            if(BuildConfig.FLAVOR == "free"){
                                starButton.visibility = View.GONE
                            }

                            starButton.setOnCheckedChangeListener { _, isChecked ->
                                val teamEntity = teamDetails.team?.toTeamEntity()

                                if (isChecked) {
                                    insertTeam(teamEntity)

                                    Toast.makeText(
                                        context, "Equipo " + teamDetails.team?.name + " añadido a favoritos",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    deleteTeam(teamEntity?.id)
                                    Toast.makeText(
                                        context, "Equipo " + teamDetails.team?.name + " eliminado de favoritos",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                    Status.ERROR ->{

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
}