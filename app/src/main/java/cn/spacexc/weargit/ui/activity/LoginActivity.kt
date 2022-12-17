package cn.spacexc.weargit.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import cn.spacexc.weargit.TAG
import cn.spacexc.weargit.ui.component.RegularBackgroundWithTitleAndBackArrow
import cn.spacexc.weargit.utils.QRCodeUtil
import cn.spacexc.weargit.viewmodel.LoginActivityViewModel

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

class LoginActivity : ComponentActivity() {
    private val viewModel by viewModels<LoginActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAuthCode { isSuccess, token ->
            if(isSuccess && token != null) {
                Log.d(TAG, "token: $token")
                //startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        }
        setContent {
            val authCode by viewModel.authCodeObj.observeAsState()
            RegularBackgroundWithTitleAndBackArrow(title = "登录", onBack = ::finish) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (authCode?.userCode != null) "扫描二维码后在手机上输入\n${authCode?.userCode}\n以验证" else "加载中",
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                    if (authCode?.verificationUri != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(10.dp)
                                )
                                .background(Color.White)

                                .padding(8.dp)
                        ) {
                            Image(
                                bitmap = QRCodeUtil.createQRCodeBitmap(
                                    authCode?.verificationUri,
                                    150,
                                    150,
                                    QRCodeUtil.ERROR_CORRECTION_L
                                )!!.asImageBitmap(), contentDescription = null
                            )
                        }
                        /*Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = {
                            viewModel.getAuthState(authCode?.deviceCode!!)
                        }) {
                            Text(text = "继续")
                        }*/
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}