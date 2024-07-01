package es.upsa.mimo.android.laligaapp.model.players

import com.google.gson.annotations.SerializedName


data class PlayerResp (

  @SerializedName("player"     ) var player     : Player?               = Player(),
  @SerializedName("statistics" ) var statistics : ArrayList<Statistics> = arrayListOf()

)