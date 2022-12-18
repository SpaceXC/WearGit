package cn.spacexc.weargit.dataclass.repo.commit.record


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommitRecordsItem(
    @SerialName("author")
    val author: Author,
    @SerialName("comments_url")
    val commentsUrl: String,
    @SerialName("commit")
    val commit: Commit,
    @SerialName("committer")
    val committer: CommitterX,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("node_id")
    val nodeId: String,
    @SerialName("parents")
    val parents: List<Parent>,
    @SerialName("sha")
    val sha: String,
    @SerialName("url")
    val url: String
)