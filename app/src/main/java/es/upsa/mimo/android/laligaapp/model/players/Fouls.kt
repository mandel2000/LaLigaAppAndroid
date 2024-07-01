package es.upsa.mimo.android.laligaapp.model.players

import com.google.gson.annotations.SerializedName


data class Fouls (

  @SerializedName("drawn"     ) var drawn     : String? = null,
  @SerializedName("committed" ) var committed : String? = null

)