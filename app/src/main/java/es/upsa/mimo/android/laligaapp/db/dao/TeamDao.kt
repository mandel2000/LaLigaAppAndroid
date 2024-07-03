package es.upsa.mimo.android.laligaapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import es.upsa.mimo.android.laligaapp.db.entities.TeamEntity
import es.upsa.mimo.android.laligaapp.model.teams.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Insert
    suspend fun insertTeam(team: TeamEntity)

    @Query("DELETE FROM TeamEntity WHERE id = :id")
    suspend fun deleteTeam(id: Int)

    @Query("SELECT * FROM TeamEntity")
    fun getAllTeams() : Flow<List<TeamEntity>>

}