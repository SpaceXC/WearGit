package cn.spacexc.weargit.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeTextDefaults
import cn.spacexc.weargit.ui.theme.GithubBackgroundGay
import cn.spacexc.weargit.ui.theme.WearGitTheme
import cn.spacexc.weargit.ui.theme.googleSansFamily
import cn.spacexc.weargit.ui.theme.puhuiFamily

/**
 * Created by Xiaochang on 2022/9/16.
 * Modified on 2022/12/17
 * I'm very cute so please be nice to my code!
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 */

@Composable
fun RegularBackgroundWithTitleAndBackArrow(
    title: String,
    onBack: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val timeSource = TimeTextDefaults.timeSource("HH:mm")
    val timeText = timeSource.currentTime
    var textHeight by remember { mutableStateOf(0.dp) }
    val localDensity = LocalDensity.current
    WearGitTheme(darkTheme = true) {
        Column(
            Modifier
                .fillMaxSize()
                .background(GithubBackgroundGay)) {
            Column(Modifier.fillMaxWidth()) {
                Spacer(Modifier.height(6.dp))
                if (isRound()) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = puhuiFamily,
                        color = Color.White,
                        modifier = Modifier
                            .onGloballyPositioned {
                                textHeight = with(localDensity) {
                                    it.size.height.toDp()
                                }
                            }
                            .align(Alignment.CenterHorizontally)
                            .clickable(
                                onClick = {
                                    onBack()
                                },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ), maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
                } else {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(start = 7.5f.dp, end = 7.5f.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable(
                                onClick = {
                                    onBack()
                                },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIos,
                                contentDescription = null,
                                modifier = Modifier
                                    .height(16.dp)
                                    .width(16.dp)
                                    .align(Alignment.CenterVertically)
                                    .offset(y = 0.9f.dp),
                                tint = Color.White
                            )
                            //Spacer(Modifier.width(2.dp))
                            Text(
                                text = title,
                                fontSize = 16.sp,
                                fontFamily = puhuiFamily,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier
                                    .onGloballyPositioned {
                                        textHeight = with(localDensity) {
                                            it.size.height.toDp()
                                        }
                                    }
                                    .align(Alignment.CenterVertically),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = timeText,
                                fontSize = 16.sp,
                                fontFamily = googleSansFamily,
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                                modifier = Modifier.align(Alignment.CenterVertically), maxLines = 1
                            )
                        }
                    }
                }
                Spacer(Modifier.height(6.dp))
            }   //标题栏
            content()
        }   //内容
    }
}