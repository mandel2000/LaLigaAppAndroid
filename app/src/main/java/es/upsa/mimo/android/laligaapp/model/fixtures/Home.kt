package es.upsa.mimo.android.laligaapp.model.fixtures

import com.google.gson.annotations.SerializedName


data class Home (

  @SerializedName("id"     ) var id     : Int?     = null,
  @SerializedName("logo"   ) var logo   : String?  = null,
  @SerializedName("name"   ) var name   : String?  = null,
  @SerializedName("winner" ) var winner : Boolean? = null

)