package cn.spacexc.weargit.dataclass.auth.code

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthCode(
    @SerialName("device_code")
    val deviceCode: String,
    @SerialName("expires_in")
    val expiresIn: Int,
    @SerialName("interval")
    val interval: Int,
    @SerialName("user_code")
    val userCode: String,
    @SerialName("verification_uri")
    val verificationUri: String
)