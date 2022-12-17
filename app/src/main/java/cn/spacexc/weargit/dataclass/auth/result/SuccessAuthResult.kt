package cn.spacexc.weargit.dataclass.auth.result


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuccessAuthResult(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("scope")
    val scope: String,
    @SerialName("token_type")
    val tokenType: String
)