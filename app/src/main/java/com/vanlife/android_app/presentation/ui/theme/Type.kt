package com.vanlife.android_app.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vanlife.android_app.R

private val Segoe_UI = FontFamily(
    Font(R.font.roboto_thin, FontWeight.W100),
    Font(R.font.roboto_light, FontWeight.W300),
    Font(R.font.roboto_regular, FontWeight.W400),
    Font(R.font.roboto_medium, FontWeight.W500),
    Font(R.font.roboto_bold, FontWeight.W700),
    Font(R.font.roboto_black, FontWeight.W900)
)

val SegoeTypography = Typography(
    h1 = TextStyle(
        fontFamily = Segoe_UI,
        fontWeight = FontWeight.W500,
        fontSize = 30.sp
    ),
    h2 = TextStyle(
        fontFamily = Segoe_UI,
        fontWeight = FontWeight.W500,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = Segoe_UI,
        fontWeight = FontWeight.W400,
        fontSize = 19.sp
    ),
    h4 = TextStyle(
        fontFamily = Segoe_UI,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp
    ),
    h5 = TextStyle(
        fontFamily = Segoe_UI,
        fontWeight = FontWeight.W300,
        fontSize = 16.sp
    ),
    h6 = TextStyle(
        fontFamily = Segoe_UI,
        fontWeight = FontWeight.W100,
        fontSize = 21.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Segoe_UI,
        fontWeight = FontWeight.W400,
        fontSize = 17.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Segoe_UI,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = Segoe_UI,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = Segoe_UI,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = Segoe_UI,
        fontWeight = FontWeight.W400,
        fontSize = 15.sp,
        color = Color.White
    ),
    caption = TextStyle(
        fontFamily = Segoe_UI,
        fontWeight = FontWeight.W300,
        fontSize = 13.sp
    ),
    overline = TextStyle(
        fontFamily = Segoe_UI,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    )
)