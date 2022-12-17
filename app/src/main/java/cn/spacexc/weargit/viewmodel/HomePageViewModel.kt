package cn.spacexc.weargit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.spacexc.weargit.api.repository.RepositoryApi
import cn.spacexc.weargit.dataclass.repo.user.UserRepositoriesItem
import cn.spacexc.weargit.utils.DataUtils
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

class HomePageViewModel : ViewModel() {
    val isRefreshing = MutableLiveData(false)

    val repositoryList = MutableLiveData<List<UserRepositoriesItem>>()

    fun getUserRepos() {
        if(DataUtils.getString("oauthToken", "").isEmpty()) return
        isRefreshing.value = true
        RepositoryApi.getUserRepositoryByOAuth {
            MainScope().launch {
                repositoryList.value = it
                isRefreshing.value = false
            }
        }
    }
}