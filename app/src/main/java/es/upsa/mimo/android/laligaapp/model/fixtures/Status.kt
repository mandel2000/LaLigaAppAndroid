package es.upsa.mimo.android.laligaapp.model.fixtures

import com.google.gson.annotations.SerializedName


data class Status (

  @SerializedName("elapsed" ) var elapsed : Int?    = null,
  @SerializedName("long"    ) var long    : String? = null,
  @SerializedName("short"   ) var short   : String? = null

)