package cn.spacexc.weargit.dataclass.repo.info


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Permissions(
    @SerialName("admin")
    val admin: Boolean,
    @SerialName("pull")
    val pull: Boolean,
    @SerialName("push")
    val push: Boolean
)