package cn.spacexc.weargit

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import cn.spacexc.weargit.manager.SecretManager

/* 
WatchGit Copyright (C) 2022 XC
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
const val TAG = "WearGitTag"

class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        Log.d(
            TAG,
            "secret: \n${
                SecretManager.encrypt(
                    SecretManager.cliendId
                )
            }"
        )
        Log.d(
            TAG,
            "secret: ${
                SecretManager.decrypt(
                    "1-0//6-1//3-0//0-0//2-1//7-0//4-0//2-0//6-0//0-0//2-1//1-0//7-0//8-1//0-1//3-1//5-1//1-1//5-0//4-1//|-|4\\5c3ig480b::-71/e54"
                )
            }"

        )
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}