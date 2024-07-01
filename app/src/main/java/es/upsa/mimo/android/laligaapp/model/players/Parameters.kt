package es.upsa.mimo.android.laligaapp.model.players

import com.google.gson.annotations.SerializedName


data class Parameters (

  @SerializedName("team"   ) var team   : String? = null,
  @SerializedName("season" ) var season : String? = null

)