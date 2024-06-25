package es.upsa.mimo.android.laligaapp.model.standings

import com.google.gson.annotations.SerializedName


data class StandingsResponse (

  @SerializedName("errors"     ) var errors     : ArrayList<String>   = arrayListOf(),
  @SerializedName("get"        ) var get        : String?             = null,
  @SerializedName("paging"     ) var paging     : Paging?             = Paging(),
  @SerializedName("parameters" ) var parameters : Parameters?         = Parameters(),
  @SerializedName("response"   ) var response   : ArrayList<Response> = arrayListOf(),
  @SerializedName("results"    ) var results    : Int?                = null

)