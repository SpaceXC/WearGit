package cn.spacexc.weargit.dataclass.user.repository


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UserRepositories(@SerialName("") val arr: List<UserRepositoriesItem>)