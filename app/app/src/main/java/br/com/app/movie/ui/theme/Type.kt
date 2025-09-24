package br.com.app.movie.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.app.movie.R

val Nunito = FontFamily(Font(R.font.nunito))

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.sp
    )
)