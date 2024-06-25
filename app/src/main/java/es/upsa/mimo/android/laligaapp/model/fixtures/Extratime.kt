package es.upsa.mimo.android.laligaapp.model.fixtures

import com.google.gson.annotations.SerializedName


data class Extratime (

  @SerializedName("away" ) var away : String? = null,
  @SerializedName("home" ) var home : String? = null

)