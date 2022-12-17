package cn.spacexc.weargit.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cn.spacexc.weargit.TAG
import cn.spacexc.weargit.dataclass.auth.code.AuthCode
import cn.spacexc.weargit.dataclass.auth.result.ErrorAuthResult
import cn.spacexc.weargit.dataclass.auth.result.FailureAuthResult
import cn.spacexc.weargit.dataclass.auth.result.SuccessAuthResult
import cn.spacexc.weargit.manager.SecretManager
import cn.spacexc.weargit.utils.DataUtils
import cn.spacexc.weargit.utils.NetworkUtils
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
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

class LoginActivityViewModel(application: Application) : AndroidViewModel(application) {
    val authCodeObj = MutableLiveData<AuthCode>()
    val clientId = SecretManager.decrypt(
        SecretManager.cliendId
    )

    fun getAuthCode(callback: (Boolean, String?) -> Unit) {
        NetworkUtils.postUrl<AuthCode>(
            url = "https://github.com/login/device/code?client_id=$clientId&scopes=user%20repo", callback = {
                MainScope().launch {
                    authCodeObj.value = it
                    viewModelScope.launch {
                        while(true) {
                            getAuthState(it.deviceCode, callback)
                            delay(it.interval * 1000L)
                        }
                    }
                }
            }
        )
    }

    private suspend fun getAuthState(deviceCode: String, callback: (Boolean, String?) -> Unit) {
            val response =
                NetworkUtils.client.submitForm(
                    "https://github.com/login/oauth/access_token?client_id=$clientId&device_code=$deviceCode&grant_type=urn:ietf:params:oauth:grant-type:device_code",
                    formParameters = parametersOf(),
                    encodeInQuery = true
                ) {
                    accept(ContentType.Application.Json)
                    method = HttpMethod.Post
                }
            Log.d(TAG, "getAuthCode: ${response.bodyAsText()}")
            try {
                val body = response.body<SuccessAuthResult>()
                callback(true, body.accessToken)
                DataUtils.saveString("oauthToken", "${body.tokenType} ${body.accessToken}")
            } catch (_: JsonConvertException) {
                try {
                    val body = response.body<FailureAuthResult>()
                    callback(false, null)
                } catch (_: JsonConvertException) {
                    val body = response.body<ErrorAuthResult>()
                    callback(false, null)
                }
            }
        }

}