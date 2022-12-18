package cn.spacexc.weargit.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.spacexc.weargit.ui.component.RegularBackgroundWithTitleAndBackArrow
import cn.spacexc.weargit.viewmodel.RepositoryDetailViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

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

class FileContentActivity : ComponentActivity() {
    private val viewModel by viewModels<RepositoryDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fileUrl = intent.getStringExtra("fileUrl")
        viewModel.getFileContent(fileUrl ?: "")
        setContent {
            RegularBackgroundWithTitleAndBackArrow(
                title = fileUrl?.split("/")?.last()?.split("?")?.get(0) ?: "", onBack = ::finish
            ) {
                val fileContent by viewModel.fileContent.observeAsState()
                val isRefreshing by viewModel.isRefreshing.observeAsState()
                val refreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing ?: false)
                SwipeRefresh(
                    state = refreshState,
                    onRefresh = { viewModel.getFileContent(fileUrl ?: "") }) {
                    Text(
                        text = fileContent ?: "",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                            .verticalScroll(rememberScrollState()),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}