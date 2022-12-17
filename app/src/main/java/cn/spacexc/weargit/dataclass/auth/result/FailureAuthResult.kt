package cn.spacexc.weargit.dataclass.auth.result


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FailureAuthResult(
    @SerialName("error")
    val error: String,
    @SerialName("error_description")
    val errorDescription: String,
    @SerialName("error_uri")
    val errorUri: String
)