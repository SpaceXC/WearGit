package cn.spacexc.weargit.dataclass.repo.commit.record


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Commit(
    @SerialName("author")
    val author: AuthorX,
    @SerialName("comment_count")
    val commentCount: Int,
    @SerialName("committer")
    val committer: Committer,
    @SerialName("message")
    val message: String,
    @SerialName("tree")
    val tree: Tree,
    @SerialName("url")
    val url: String,
    @SerialName("verification")
    val verification: Verification
)