package cn.spacexc.weargit.api.repository

import cn.spacexc.weargit.dataclass.repo.user.UserRepositories
import cn.spacexc.weargit.dataclass.repo.user.UserRepositoriesItem
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
}