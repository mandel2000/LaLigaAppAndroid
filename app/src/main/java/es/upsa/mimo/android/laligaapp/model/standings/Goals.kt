package es.upsa.mimo.android.laligaapp.model.standings

import com.google.gson.annotations.SerializedName


data class Goals (

  @SerializedName("against" ) var goalsAgainst : Int? = null,
  @SerializedName("for"     ) var goalsFor     : Int? = null

)