package cn.spacexc.weargit.dataclass.repo.commit.record


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Verification(
    @SerialName("payload")
    val payload: String?,
    @SerialName("reason")
    val reason: String,
    @SerialName("signature")
    val signature: String?,
    @SerialName("verified")
    val verified: Boolean
)