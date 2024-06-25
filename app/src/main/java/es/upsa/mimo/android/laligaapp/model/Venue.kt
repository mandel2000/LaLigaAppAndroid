package es.upsa.mimo.android.laligaapp

import com.google.gson.annotations.SerializedName


data class Venue (

  @SerializedName("address"  ) var address  : String? = null,
  @SerializedName("capacity" ) var capacity : Int?    = null,
  @SerializedName("city"     ) var city     : String? = null,
  @SerializedName("id"       ) var id       : Int?    = null,
  @SerializedName("image"    ) var image    : String? = null,
  @SerializedName("name"     ) var name     : String? = null,
  @SerializedName("surface"  ) var surface  : String? = null

)