package cn.spacexc.weargit.dataclass.repo.commit.record


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Committer(
    @SerialName("date")
    val date: String,
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String
)