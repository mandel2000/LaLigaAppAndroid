package es.upsa.mimo.android.laligaapp.model.players

import com.google.gson.annotations.SerializedName


data class Duels (

  @SerializedName("total" ) var total : String? = null,
  @SerializedName("won"   ) var won   : String? = null

)