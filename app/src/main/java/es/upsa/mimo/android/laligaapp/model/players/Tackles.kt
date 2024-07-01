package es.upsa.mimo.android.laligaapp.model.players

import com.google.gson.annotations.SerializedName


data class Tackles (

  @SerializedName("total"         ) var total         : String? = null,
  @SerializedName("blocks"        ) var blocks        : String? = null,
  @SerializedName("interceptions" ) var interceptions : String? = null

)