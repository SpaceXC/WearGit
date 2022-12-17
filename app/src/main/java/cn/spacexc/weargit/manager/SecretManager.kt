package cn.spacexc.weargit.manager

import android.util.Log
import cn.spacexc.weargit.TAG
import kotlin.math.absoluteValue
import kotlin.random.Random

/* 
WearGit Copyright (C) 2022 XC
This program comes with ABSOLUTELY NO WARRANTY.
This is free software, and you are welcome to redistribute it under certain conditions.
*/

/*
 * Created by XC on 2022/12/16.
 * I'm very cute so please be nice to my code!
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 */

object SecretManager {

    val cliendId = "5-0//5-1//5-0//5-0//3-0//3-1//2-1//0-0//7-1//6-0//4-1//6-1//7-1//3-1//4-1//6-1//3-1//5-0//8-1//0-0//|-|8]7h8_a2+6`3,23.1k(8"

    fun encrypt(content: String): String {
        var encryptedString = ""
        var nums = ""
        val array = content.toCharArray()
        array.forEach {
            val bool = (Random.nextInt() % 2).absoluteValue
            var thisChar = it
            val randomNum = Random.nextInt(0, 9)
            if (bool == 0) {
                thisChar += randomNum
            } else {
                thisChar -= randomNum
            }
            encryptedString += thisChar
            nums += "$randomNum-$bool//"
        }
        encryptedString = "$nums|-|$encryptedString"
        Log.d(TAG, "encrypt: $encryptedString")
        return encryptedString
    }

    fun decrypt(content: String): String {
        var decryptedString = ""
        val nums = content.split("|-|")[0].split("//")
        val array = content.split("|-|")[1].toCharArray()
        array.forEachIndexed { i, char ->
            var thisChar = char
            val currentNum = nums[i]
            val num = currentNum.split("-")[0].toInt()
            val bool = currentNum.split("-")[1].toInt()
            if(bool == 0) {
                thisChar -= num
            }
            else {
                thisChar += num
            }
            decryptedString += thisChar
        }
        return decryptedString
    }
}