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
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import cn.spacexc.weargit.ui.component.RegularBackgroundWithTitleAndBackArrow
import cn.spacexc.weargit.ui.theme.GithubGray
import cn.spacexc.weargit.ui.theme.WearGitTheme
import cn.spacexc.weargit.utils.DataUtils
import cn.spacexc.weargit.viewmodel.HomePageViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<HomePageViewModel>()

    private val token = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch { DataUtils.getString("oauthToken", "") { token.value = it } }
        viewModel.getUserRepos()
        setContent {
            WearGitTheme(darkTheme = true) {
                val tokenVal by token.observeAsState()
                val repositories by viewModel.repositoryList.observeAsState()
                val isRefreshing by viewModel.isRefreshing.observeAsState()
                val refreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing ?: false)
                RegularBackgroundWithTitleAndBackArrow(title = "首页", onBack = ::finish) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        SwipeRefresh(
                            state = refreshState,
                            onRefresh = { viewModel.getUserRepos() },
                            refreshTriggerDistance = 40.dp
                        ) {
                            LazyColumn(contentPadding = PaddingValues(8.dp)) {
                                if (tokenVal.isNullOrEmpty()) {
                                    item {
                                        Button(onClick = {
                                            if (DataUtils.getString("oauthToken", "").isEmpty()) {
                                                startActivity(
                                                    Intent(
                                                        this@MainActivity,
                                                        LoginActivity::class.java
                                                    )
                                                )
                                            } else {
                                                token.value = DataUtils.getString("oauthToken", "")
                                                viewModel.getUserRepos()
                                            }
                                        }) {
                                            Text(text = "登录")
                                        }
                                    }
                                }
                                repositories?.forEach {
                                    item {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clip(
                                                    RoundedCornerShape(6.dp)
                                                )
                                                .background(
                                                    GithubGray
                                                )

                                                .clickable {
                                                    Intent(
                                                        this@MainActivity,
                                                        RepositoryDetailActivity::class.java
                                                    ).apply {
                                                        putExtra("repositoryName", it.fullName)
                                                        startActivity(this)
                                                    }
                                                }
                                                .padding(8.dp)
                                        ) {
                                            Text(
                                                text = it.name,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Medium
                                            )
                                            it.description?.let { desc ->
                                                Text(
                                                    text = desc,
                                                    fontSize = 12.sp,
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //viewModel.getUserRepos()
    }
}