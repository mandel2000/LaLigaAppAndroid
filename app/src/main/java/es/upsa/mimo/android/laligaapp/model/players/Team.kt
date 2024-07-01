package es.upsa.mimo.android.laligaapp.model.players

import com.google.gson.annotations.SerializedName


data class Team (

  @SerializedName("id"   ) var id   : Int?    = null,
  @SerializedName("name" ) var name : String? = null,
  @SerializedName("logo" ) var logo : String? = null

)