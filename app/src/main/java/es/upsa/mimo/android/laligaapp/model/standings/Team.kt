package es.upsa.mimo.android.laligaapp.model.standings

import com.google.gson.annotations.SerializedName


data class Team (

  @SerializedName("id"   ) var id   : Int?    = null,
  @SerializedName("logo" ) var logo : String? = null,
  @SerializedName("name" ) var name : String? = null

)