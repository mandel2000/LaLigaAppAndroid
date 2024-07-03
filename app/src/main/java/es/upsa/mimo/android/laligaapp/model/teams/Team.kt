package es.upsa.mimo.android.laligaapp.model.teams

import com.google.gson.annotations.SerializedName
import es.upsa.mimo.android.laligaapp.db.entities.TeamEntity


data class Team (

  @SerializedName("code"     ) var code     : String?  = null,
  @SerializedName("country"  ) var country  : String?  = null,
  @SerializedName("founded"  ) var founded  : Int?     = null,
  @SerializedName("id"       ) var id       : Int?     = null,
  @SerializedName("logo"     ) var logo     : String?  = null,
  @SerializedName("name"     ) var name     : String?  = null,
  @SerializedName("national" ) var national : Boolean? = null

){
  fun toTeamEntity() = TeamEntity(id = id!!, name = name!!, logo = logo!!)
}