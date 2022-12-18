package cn.spacexc.weargit.utils

import android.util.Log
import android.widget.Toast
import cn.spacexc.weargit.Application
import cn.spacexc.weargit.TAG
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.net.ConnectException

/* 
WearGit Copyright (C) 2022 XC
This program comes with ABSOLUTELY NO WARRANTY.
This is free software, and you are welcome to redistribute it under certain conditions.
*/

/*
 * Created by XC on 2022/12/15.
 * I'm very cute so please be nice to my code!
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 */

const val ACCEPT_TYPE_JSON = "application/vnd.github.json"
const val ACCEPT_TYPE_RAW = "application/vnd.github.raw"
const val ACCEPT_TYPE_HTML = "application/vnd.github.html"

object NetworkUtils {
    val client = HttpClient(Android) {
        //expectSuccess = true
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json()
        }
    }

    inline fun <reified T> getUrl(
        url: String,
        acceptType: String = ACCEPT_TYPE_JSON,
        crossinline failCallback: (e: Exception?, message: String, code: Int) -> Unit = { e, message, code ->
            MainScope().launch {
                Toast.makeText(Application.context, message, Toast.LENGTH_SHORT).show()
            }
        },
        crossinline callback: (response: T) -> Unit,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: HttpResponse = client.get(url) {
                    accept(ContentType.Application.Json)
                    headers {
                        append("Authorization", DataUtils.getString("oauthToken", ""))
                        append("Accept", acceptType)
                    }
                }
                Log.d(TAG, "getUrl: ${response.bodyAsText()}")
                if (response.status == HttpStatusCode.OK) {
                    if (acceptType == ACCEPT_TYPE_JSON) {
                        val result: T = response.body()
                        callback(result)
                    } else {
                        callback(response.bodyAsText() as T)
                    }
                } else {
                    failCallback(null, "网络请求失败，错误码${response.status.value}", response.status.value)
                }
            } catch (e: ConnectException) {
                e.printStackTrace()
                failCallback(e, "网络连接异常", -1)
            } catch (e: JsonConvertException) {
                e.printStackTrace()
                failCallback(e, "网络请求异常", -1)
            }
        }
    }

    inline fun <reified T> postUrl(
        url: String,
        acceptType: String = ACCEPT_TYPE_JSON,
        formValues: Parameters = parametersOf(),
        crossinline failCallback: (e: Exception?, message: String, code: Int) -> Unit = { e, message, code ->
            MainScope().launch {
                Toast.makeText(Application.context, message, Toast.LENGTH_SHORT).show()
            }
        },
        crossinline callback: (response: T) -> Unit,

        ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response =
                    client.submitForm(url, formParameters = formValues, encodeInQuery = true) {
                        accept(ContentType.Application.Json)
                        method = HttpMethod.Post
                        headers {
                            append("Authorization", DataUtils.getString("oauthToken", ""))
                            append("Accept", acceptType)
                        }
                    }
                Log.d(TAG, "postUrl: ${response.bodyAsText()}")
                if (response.status == HttpStatusCode.OK) {
                    if (acceptType == ACCEPT_TYPE_JSON) {
                        val result: T = response.body()
                        callback(result)
                    } else {
                        callback(response.bodyAsText() as T)
                    }
                } else {
                    failCallback(null, "网络请求失败，错误码${response.status.value}", response.status.value)
                }
            } catch (e: ConnectException) {
                e.printStackTrace()
                failCallback(e, "网络连接异常", -1)
            } catch (e: JsonConvertException) {
                e.printStackTrace()
                failCallback(e, "网络请求异常", -1)
            }
        }

    }
}