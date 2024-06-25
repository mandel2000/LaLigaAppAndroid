package es.upsa.mimo.android.laligaapp.model.teams

import com.google.gson.annotations.SerializedName


data class Parameters (

  @SerializedName("league" ) var league : String? = null,
  @SerializedName("season" ) var season : String? = null

)