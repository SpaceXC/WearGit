package cn.spacexc.weargit.utils

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import cn.spacexc.weargit.Application
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

/*
 * Created by XC on 2022/6/7.
 * I'm very cute so please be nice to my code!
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 */
private const val PREFS_NAME = "cn.spacexc.weargit.datastore"
val Context.dataStore by preferencesDataStore(
    name = PREFS_NAME
)

object DataUtils {
    private val dataStore = Application.context.dataStore

    suspend fun saveString(key: String, value: String) {
        dataStore.edit { pref -> pref[stringPreferencesKey(key)] = value }
    }

    suspend fun saveInt(key: String, value: Int) {
        dataStore.edit { pref -> pref[intPreferencesKey(key)] = value }
    }

    suspend fun saveBool(key: String, value: Boolean) {
        dataStore.edit { pref -> pref[booleanPreferencesKey(key)] = value }
    }

    fun getStringFlow(key: String, defaultValue: String = ""): Flow<String> {
        return dataStore.data.catch { e ->
            if (e is IOException) {
                e.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[stringPreferencesKey(key)] ?: defaultValue
        }
    }

    fun getString(key: String, defaultValue: String = ""): String {
        var value = defaultValue
        runBlocking {
            dataStore.data.first {
                value = it[stringPreferencesKey(key)] ?: value
                true
            }
        }
        return value
        /*dataStore.data.catch { e ->
            if (e is IOException) {
                e.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[stringPreferencesKey(key)] ?: defaultValue
        }.collect {
            onReturn(it)
        }*/
    }

    suspend fun getString(key: String, defaultValue: String = "", callback: (String) -> Unit) {
        dataStore.data.catch { e ->
            if (e is IOException) {
                e.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[stringPreferencesKey(key)] ?: defaultValue
        }.collect {
            callback(it)
        }
    }


    fun getBoolean(key: String, defaultValue: Boolean = false): Flow<Boolean> {
        return dataStore.data.catch { e ->
            if (e is IOException) {
                e.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[booleanPreferencesKey(key)] ?: defaultValue
        }
    }

    suspend fun getBoolean(
        key: String,
        defaultValue: Boolean = false,
        onReturn: (Boolean) -> Unit
    ) {
        dataStore.data.catch { e ->
            if (e is IOException) {
                e.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[booleanPreferencesKey(key)] ?: defaultValue
        }.collect {
            onReturn(it)
        }
    }

    fun saveStringInBackground(key: String, value: String, postActions: () -> Unit = {}) {
        CoroutineScope(Dispatchers.IO).launch {
            saveString(key, value)
            MainScope().launch {
                postActions()
            }
        }
    }
}