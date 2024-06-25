package es.upsa.mimo.android.laligaapp.model.standings

import com.google.gson.annotations.SerializedName


data class Standings (

  @SerializedName("all"         ) var all         : All?    = All(),
  @SerializedName("away"        ) var away        : Away?   = Away(),
  @SerializedName("description" ) var description : String? = null,
  @SerializedName("form"        ) var form        : String? = null,
  @SerializedName("goalsDiff"   ) var goalsDiff   : Int?    = null,
  @SerializedName("group"       ) var group       : String? = null,
  @SerializedName("home"        ) var home        : Home?   = Home(),
  @SerializedName("points"      ) var points      : Int?    = null,
  @SerializedName("rank"        ) var rank        : Int?    = null,
  @SerializedName("status"      ) var status      : String? = null,
  @SerializedName("team"        ) var team        : Team?   = Team(),
  @SerializedName("update"      ) var update      : String? = null

)