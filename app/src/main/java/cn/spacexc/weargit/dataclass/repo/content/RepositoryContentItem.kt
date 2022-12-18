package cn.spacexc.weargit.dataclass.repo.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryContentItem(
    @SerialName("download_url")
    val downloadUrl: String?,
    @SerialName("git_url")
    val gitUrl: String,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("_links")
    val links: Links,
    @SerialName("name")
    val name: String,
    @SerialName("path")
    val path: String,
    @SerialName("sha")
    val sha: String,
    @SerialName("size")
    val size: Int,
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String
)