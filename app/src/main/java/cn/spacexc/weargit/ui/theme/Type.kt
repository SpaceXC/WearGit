package cn.spacexc.weargit.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cn.spacexc.weargit.R

val monaSansFamily = FontFamily(
    Font(resId = R.font.monasans_medium, weight = FontWeight.Normal),
    Font(resId = R.font.monasans_bold, weight = FontWeight.Medium),
    Font(resId = R.font.monasans_extrabold, weight = FontWeight.Bold)
)

val puhuiFamily = FontFamily(
    Font(resId = R.font.puhui_medium, weight = FontWeight.Medium)
)

val googleSansFamily = FontFamily(
    Font(resId = R.font.googlesans_medium, weight = FontWeight.Medium)
)

// Set of Material typography styles to start with
val Typography = Typography(defaultFontFamily = monaSansFamily,
    body1 = TextStyle(
        fontFamily = monaSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color.White
    ),
    button = TextStyle(
        fontFamily = monaSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color.White,
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)