package cn.spacexc.weargit.dataclass.repo.issue


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Milestone(
    @SerialName("closed_at")
    val closedAt: String,
    @SerialName("closed_issues")
    val closedIssues: Int,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("creator")
    val creator: Creator,
    @SerialName("description")
    val description: String,
    @SerialName("due_on")
    val dueOn: String,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("id")
    val id: Int,
    @SerialName("labels_url")
    val labelsUrl: String,
    @SerialName("node_id")
    val nodeId: String,
    @SerialName("number")
    val number: Int,
    @SerialName("open_issues")
    val openIssues: Int,
    @SerialName("state")
    val state: String,
    @SerialName("title")
    val title: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("url")
    val url: String
)