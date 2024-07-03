package es.upsa.mimo.android.laligaapp.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.upsa.mimo.android.laligaapp.model.teams.Team

@Entity
data class TeamEntity (
    @PrimaryKey
    val id: Int,
    val name: String,
    val logo: String
    ){

    fun toTeam() = Team(id = id, name = name, logo = logo)

}