package cn.spacexc.weargit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.spacexc.weargit.api.repository.RepositoryApi
import cn.spacexc.weargit.dataclass.repo.content.RepositoryContentItem
import cn.spacexc.weargit.dataclass.repo.issue.IssuesItem
import cn.spacexc.weargit.utils.ACCEPT_TYPE_RAW
import cn.spacexc.weargit.utils.NetworkUtils
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

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

class RepositoryDetailViewModel : ViewModel() {
    val isRefreshing = MutableLiveData(false)

    val repositoryContents = MutableLiveData<List<RepositoryContentItem>>()
    val fileContent = MutableLiveData<String>()
    val repositoryIssues = MutableLiveData<List<IssuesItem>>()

    fun getRepositoryContent(name: String, path: String = "/") {
        isRefreshing.value = true
        RepositoryApi.getRepositoryContents(name, path) {
            MainScope().launch {
                repositoryContents.value = it
                isRefreshing.value = false
            }
        }
    }

    fun getFileContent(url: String) {
        isRefreshing.value = true
        NetworkUtils.getUrl<String>(url, acceptType = ACCEPT_TYPE_RAW) {
            MainScope().launch {
                fileContent.value = it
                isRefreshing.value = false
            }
        }
    }

    fun getRepositoryIssues(name: String) {
        isRefreshing.value = true
        RepositoryApi.getRepositoryIssues(name) {
            repositoryIssues.value = it
        }
    }
}