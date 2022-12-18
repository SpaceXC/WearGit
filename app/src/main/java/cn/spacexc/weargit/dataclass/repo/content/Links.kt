package cn.spacexc.weargit.dataclass.repo.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    @SerialName("git")
    val git: String,
    @SerialName("html")
    val html: String,
    @SerialName("self")
    val self: String
)