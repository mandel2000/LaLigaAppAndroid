package es.upsa.mimo.android.laligaapp.model.players

import com.google.gson.annotations.SerializedName


data class Shots (

  @SerializedName("total" ) var total : String? = null,
  @SerializedName("on"    ) var on    : String? = null

)