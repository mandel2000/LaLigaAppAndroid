package es.upsa.mimo.android.laligaapp.model.fixtures

import com.google.gson.annotations.SerializedName


data class FixtureResp (

  @SerializedName("fixture" ) var fixture : Fixture? = Fixture(),
  @SerializedName("goals"   ) var goals   : Goals?   = Goals(),
  @SerializedName("league"  ) var league  : League?  = League(),
  @SerializedName("score"   ) var score   : Score?   = Score(),
  @SerializedName("teams"   ) var teams   : Teams?   = Teams()

)