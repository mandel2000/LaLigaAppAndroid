package es.upsa.mimo.android.laligaapp.model.fixtures

import com.google.gson.annotations.SerializedName


data class Venue (

  @SerializedName("city" ) var city : String? = null,
  @SerializedName("id"   ) var id   : Int?    = null,
  @SerializedName("name" ) var name : String? = null

)