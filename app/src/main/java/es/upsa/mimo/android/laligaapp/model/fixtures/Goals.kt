package es.upsa.mimo.android.laligaapp.model.fixtures

import com.google.gson.annotations.SerializedName


data class Goals (

  @SerializedName("away" ) var away : Int? = null,
  @SerializedName("home" ) var home : Int? = null

)