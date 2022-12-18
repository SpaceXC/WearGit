package cn.spacexc.weargit.dataclass.repo.issue


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PullRequest(
    @SerialName("diff_url")
    val diffUrl: String,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("patch_url")
    val patchUrl: String,
    @SerialName("url")
    val url: String
)