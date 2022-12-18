package cn.spacexc.weargit.api.repository

import cn.spacexc.weargit.dataclass.repo.content.RepositoryContentItem
import cn.spacexc.weargit.dataclass.repo.issue.IssuesItem
import cn.spacexc.weargit.dataclass.user.repository.UserRepositoriesItem
import cn.spacexc.weargit.utils.NetworkUtils

/* 
WearGit Copyright (C) 2022 XC
This program comes with ABSOLUTELY NO WARRANTY.
This is free software, and you are welcome to redistribute it under certain conditions.
*/

/*
 * Created by XC on 2022/12/17.
 * I'm very cute so please be nice to my code!
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 */

object RepositoryApi {
    fun getUserRepositoryByOAuth(onResult: (List<UserRepositoriesItem>) -> Unit) {
        NetworkUtils.getUrl<List<UserRepositoriesItem>>("https://api.github.com/users/SpaceXC/repos") {
            onResult(it)
        }
    }

    fun getRepositoryContents(
        repositoryName: String,
        path: String = "/",
        onResult: (List<RepositoryContentItem>) -> Unit
    ) {
        NetworkUtils.getUrl<List<RepositoryContentItem>>("https://api.github.com/repos/$repositoryName/contents$path") {
            onResult(it)
        }
    }

    fun getRepositoryIssues(repositoryName: String, onResult: (List<IssuesItem>) -> Unit) {
        NetworkUtils.getUrl<List<IssuesItem>>("https://api.github.com/repos/$repositoryName/issues") {
            onResult(it)
        }
    }
}