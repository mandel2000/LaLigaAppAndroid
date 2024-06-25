package es.upsa.mimo.android.laligaapp.model.teams

import com.google.gson.annotations.SerializedName


data class Response (

  @SerializedName("team"  ) var team  : Team?  = Team(),
  @SerializedName("venue" ) var venue : Venue? = Venue()

)