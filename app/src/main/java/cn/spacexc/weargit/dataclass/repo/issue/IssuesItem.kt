package cn.spacexc.weargit.dataclass.repo.issue


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IssuesItem(
    @SerialName("active_lock_reason")
    val activeLockReason: String,
    @SerialName("assignee")
    val assignee: Assignee,
    @SerialName("assignees")
    val assignees: List<Assignee>,
    @SerialName("author_association")
    val authorAssociation: String,
    @SerialName("body")
    val body: String,
    @SerialName("closed_at")
    val closedAt: Long?,
    @SerialName("closed_by")
    val closedBy: ClosedBy,
    @SerialName("comments")
    val comments: Int,
    @SerialName("comments_url")
    val commentsUrl: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("events_url")
    val eventsUrl: String,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("id")
    val id: Int,
    @SerialName("labels")
    val labels: List<Label>,
    @SerialName("labels_url")
    val labelsUrl: String,
    @SerialName("locked")
    val locked: Boolean,
    @SerialName("milestone")
    val milestone: Milestone,
    @SerialName("node_id")
    val nodeId: String,
    @SerialName("number")
    val number: Int,
    @SerialName("pull_request")
    val pullRequest: PullRequest,
    @SerialName("repository_url")
    val repositoryUrl: String,
    @SerialName("state")
    val state: String,
    @SerialName("state_reason")
    val stateReason: String,
    @SerialName("title")
    val title: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("url")
    val url: String,
    @SerialName("user")
    val user: User
)