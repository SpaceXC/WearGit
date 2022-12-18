package cn.spacexc.weargit.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.Folder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.spacexc.weargit.ui.component.RegularBackgroundWithTitleAndBackArrow
import cn.spacexc.weargit.ui.theme.GithubGray
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

class RepositoryDetailActivity : ComponentActivity() {
    private val viewModel by viewModels<RepositoryDetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repositoryName = intent.getStringExtra("repositoryName")
        viewModel.getRepositoryContent(repositoryName ?: "")
        setContent {
            val localDensity = LocalDensity.current
            val isRefreshing by viewModel.isRefreshing.observeAsState()
            val refreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing ?: false)
            val contents by viewModel.repositoryContents.observeAsState()
            RegularBackgroundWithTitleAndBackArrow(title = "仓库详情", onBack = ::finish) {
                SwipeRefresh(
                    state = refreshState,
                    onRefresh = { viewModel.getRepositoryContent(repositoryName ?: "") }) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp)
                    ) {
                        item {
                            Text(
                                text = repositoryName ?: "",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                        contents?.forEach {
                            item {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(
                                            RoundedCornerShape(6.dp)
                                        )
                                        .background(
                                            GithubGray
                                        )

                                        .clickable {
                                            if (it.type == "dir") {
                                                Intent(
                                                    this@RepositoryDetailActivity,
                                                    FolderDetailActivity::class.java
                                                ).apply {
                                                    putExtra("repositoryName", repositoryName)
                                                    putExtra("contentPath", "/${it.path}")
                                                    startActivity(this)
                                                }
                                            }
                                            if (it.type == "file") {
                                                Intent(
                                                    this@RepositoryDetailActivity,
                                                    FileContentActivity::class.java
                                                ).apply {
                                                    putExtra("fileUrl", it.links.self)
                                                    startActivity(this)
                                                }
                                            }
                                        }
                                        .padding(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    var textHeight by remember {
                                        mutableStateOf(0.dp)
                                    }
                                    Icon(
                                        imageVector = if (it.type == "file") Icons.Rounded.Description else Icons.Rounded.Folder,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = it.name,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.onGloballyPositioned {
                                            textHeight =
                                                with(localDensity) { it.size.height.toDp() }
                                        }, maxLines = 1, overflow = TextOverflow.Ellipsis
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}