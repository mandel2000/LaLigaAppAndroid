package es.upsa.mimo.android.laligaapp.model.players

import com.google.gson.annotations.SerializedName


data class PlayersResponse (

  @SerializedName("get"        ) var get        : String?             = null,
  @SerializedName("parameters" ) var parameters : Parameters?         = Parameters(),
  @SerializedName("errors"     ) var errors     : ArrayList<String>   = arrayListOf(),
  @SerializedName("results"    ) var results    : Int?                = null,
  @SerializedName("paging"     ) var paging     : Paging?             = Paging(),
  @SerializedName("response"   ) var playerResp   : ArrayList<PlayerResp> = arrayListOf()

)